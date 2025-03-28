package es.luis.canyoningApp.infrastructure.repository;

import es.luis.canyoningApp.domain.model.Canyon;
import es.luis.canyoningApp.domain.model.LocationCanyon;
import es.luis.canyoningApp.domain.model.SimpleCanyon;
import es.luis.canyoningApp.domain.repository.CanyonRepository;
import es.luis.canyoningApp.infrastructure.entity.CanyonEntity;
import es.luis.canyoningApp.infrastructure.entity.SimpleCanyonEntity;
import es.luis.canyoningApp.infrastructure.jpaRepository.*;
import es.luis.canyoningApp.infrastructure.mapper.CanyonRepositoryMapper;
import es.luis.canyoningApp.infrastructure.mapper.SimpleCanyonRepositoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CanyonRepositoryImpl implements CanyonRepository {

    @Autowired
    private CanyonEntityRepository canyonEntityRepository;
    @Autowired
    private SimpleCanyonEntityRepository simpleCanyonEntityRepository;

    @Autowired
    private CanyonRepositoryMapper canyonRepositoryMapper;

    @Autowired
    private SimpleCanyonRepositoryMapper simpleCanyonRepositoryMapper;

    @Autowired
    private CanyonDescentEntityRepository canyonDescentEntityRepository;

    @Autowired
    private CanyonDifficultyEntityRepository canyonDifficultyEntityRepository;

    @Autowired
    private CanyonLinkEntityRepository canyonLinkEntityRepository;

    @Autowired
    private CanyonProhibitionEntityRepository canyonProhibitionEntityRepository;

    @Autowired
    private CanyonRappelingEntityRepository canyonRappelingEntityRepository;

    @Autowired
    private CanyonScheduleEntityRepository canyonScheduleEntityRepository;

    @Autowired
    private CanyonCanyonNearEntityRepository canyonCanyonNearEntityRepository;

    @Autowired
    private CanyonControlLevelEntityRepository canyonControlLevelEntityRepository;

    @Autowired
    CanyonLocationEntityRepository canyonLocationEntityRepository;

    @Override
    public Canyon getCanyonById(Long canyonId) {
        return canyonRepositoryMapper.canyonEntityToCanyon(
                canyonEntityRepository.findById(canyonId).orElseThrow());
    }

    @Override
    public Page<SimpleCanyon> getCanyons(
            String name,
            String season,
            String river,
            String country,
            String population,
            Pageable pageable) {
        return canyonEntityRepository
                .getCanyons(name, season, river, country, population, pageable)
                .map(canyonRepositoryMapper::canyonEntityToSimpleCanyon);
    }

    @Override
    public void deleteCanyon(SimpleCanyon simpleCanyon) {
        deleteAllListFromCanyon(simpleCanyon.getCanyonId());
        simpleCanyonEntityRepository.save(
                canyonRepositoryMapper.simpleCanyonToSimpleCanyonEntity(simpleCanyon));
    }

    @Transactional
    @Override
    public Canyon createCanyon(Canyon canyon) {
        CanyonEntity canyonEntity = saveSimpleCanyonAndPrepareCanyonEntityFeatures(canyon);
        saveAllFeatures(canyonEntity);
        return canyonRepositoryMapper.canyonEntityToCanyon(canyonEntity);
    }

    @Transactional
    private void saveAllFeatures(CanyonEntity canyonEntity) {
        canyonDescentEntityRepository.saveAll(canyonEntity.getCanyonDescent());
        canyonDifficultyEntityRepository.saveAll(canyonEntity.getCanyonDifficulty());
        canyonLinkEntityRepository.saveAll(canyonEntity.getCanyonLink());
        canyonProhibitionEntityRepository.saveAll(canyonEntity.getCanyonProhibition());
        canyonRappelingEntityRepository.saveAll(canyonEntity.getCanyonRappeling());
        canyonScheduleEntityRepository.saveAll(canyonEntity.getCanyonSchedule());
        canyonCanyonNearEntityRepository.saveAll(canyonEntity.getCanyonCanyonNear());
        canyonControlLevelEntityRepository.saveAll(canyonEntity.getCanyonControlLevel());
    }

    @Override
    @Transactional
    public Canyon updateCanyon(Canyon canyon) {
        deleteAllListFromCanyon(canyon.getCanyonId());
        CanyonEntity canyonEntity = saveSimpleCanyonAndPrepareCanyonEntityFeatures(canyon);
        saveAllFeatures(canyonEntity);
        return canyonRepositoryMapper.canyonEntityToCanyon(canyonEntity);
    }

    @Override
    public List<LocationCanyon> getLocations() {
        return canyonEntityRepository.findAllCanyons().stream()
                .map(canyonRepositoryMapper::canyonEntityToLocationCanyon)
                .toList();
    }

    @Transactional
    private void deleteAllListFromCanyon(Long canyonId) {

        canyonRappelingEntityRepository.deleteByCanyonId(canyonId);
        canyonDescentEntityRepository.deleteByCanyonId(canyonId);
        canyonScheduleEntityRepository.deleteByCanyonId(canyonId);
        canyonLinkEntityRepository.deleteByCanyonId(canyonId);
        canyonProhibitionEntityRepository.deleteByCanyonId(canyonId);
        canyonDifficultyEntityRepository.deleteByCanyonId(canyonId);
        canyonCanyonNearEntityRepository.deleteByCanyonId(canyonId);
        canyonControlLevelEntityRepository.deleteByCanyonId(canyonId);
    }

    @Transactional
    private CanyonEntity saveSimpleCanyonAndPrepareCanyonEntityFeatures(Canyon canyon) {

        SimpleCanyonEntity save =
                simpleCanyonEntityRepository.save(
                        simpleCanyonRepositoryMapper.simpleCanyonToSimpleCanyonEntity(canyon));

        Long canyonId = save.getCanyonId();
        canyon.setCanyonId(canyonId);
        CanyonEntity canyonEntity = canyonRepositoryMapper.canyonToCanyonEntity(canyon);
        canyonEntity.getLocation().setCanyonId(save.getCanyonId());
        canyonLocationEntityRepository.save(canyonEntity.getLocation());
        canyonEntity
                .getCanyonRappeling()
                .forEach(
                        canyonRappeling -> {
                            if (canyonRappeling.getDescentNumber() == null) {
                                canyonRappeling.setDescentNumber(1);
                            }
                            canyonRappeling.setCanyonId(canyonId);
                            canyonRappeling.setCanyon(
                                    canyonRepositoryMapper.canyonEntityToSimpleCanyonEntity(canyonEntity));
                        });
        canyonEntity
                .getCanyonDescent()
                .forEach(
                        canyonDescent -> {
                            if (canyonDescent.getDescentNumber() == null) {
                                canyonDescent.setDescentNumber(1);
                            }
                            canyonDescent.setCanyonId(canyonId);
                            canyonDescent.setCanyon(
                                    canyonRepositoryMapper.canyonEntityToSimpleCanyonEntity(canyonEntity));
                        });
        canyonEntity
                .getCanyonSchedule()
                .forEach(
                        canyonSchedule -> {
                            if (canyonSchedule.getDescentNumber() == null) {
                                canyonSchedule.setDescentNumber(1);
                            }
                            canyonSchedule.setCanyonId(canyonId);
                            canyonSchedule.setCanyon(
                                    canyonRepositoryMapper.canyonEntityToSimpleCanyonEntity(canyonEntity));
                        });
        canyonEntity
                .getCanyonLink()
                .forEach(
                        canyonLink ->
                                canyonLink.setCanyon(
                                        canyonRepositoryMapper.canyonEntityToSimpleCanyonEntity(canyonEntity)));
        canyonEntity
                .getCanyonProhibition()
                .forEach(
                        canyonProhibition ->
                                canyonProhibition.setCanyon(
                                        canyonRepositoryMapper.canyonEntityToSimpleCanyonEntity(canyonEntity)));
        canyonEntity
                .getCanyonDifficulty()
                .forEach(
                        canyonDifficulty -> {
                            if (canyonDifficulty.getDescentNumber() == null) {
                                canyonDifficulty.setDescentNumber(1);
                            }
                            canyonDifficulty.setCanyonId(canyonId);
                            canyonDifficulty.setCanyon(
                                    canyonRepositoryMapper.canyonEntityToSimpleCanyonEntity(canyonEntity));
                        });
        canyonEntity
                .getCanyonCanyonNear()
                .forEach(
                        canyonCanyonNear -> {
                            canyonCanyonNear.setPrincipalCanyon(canyonId);
                            canyonCanyonNear.setCanyon(
                                    canyonRepositoryMapper.canyonEntityToSimpleCanyonEntity(canyonEntity));
                        });
        canyonEntity
                .getCanyonControlLevel()
                .forEach(
                        canyonControlLevel -> {
                            canyonControlLevel.setCanyonId(canyonId);
                            if (!canyonControlLevel.getControlPoint().endsWith(".jpg")) {
                                canyonControlLevel.setControlPoint(canyonControlLevel.getControlPoint() + ".jpg");
                            }
                        });
        return canyonEntity;
    }
}
