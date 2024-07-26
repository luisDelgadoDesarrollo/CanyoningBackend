package es.luis.canyoningApp.infrastructure.repository;

import es.luis.canyoningApp.domain.model.Canyon;
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

@Repository
@Transactional

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

    @Override
    public Canyon getCanyonById(Long canyonId) {
        return canyonRepositoryMapper.canyonEntityToCanyon(
                canyonEntityRepository.findById(canyonId).orElseThrow());
    }

    @Override
    public Page<SimpleCanyon> getCanyons(
            String name, String season, String river, String population, Pageable pageable) {
        return simpleCanyonEntityRepository
                .getCanyons(name, season, river, population, pageable)
                .map(simpleCanyonRepositoryMapper::simpleCanyonEntityToSimpleCanyon);
    }

    @Override
    public void deleteCanyon(SimpleCanyon simpleCanyon) {
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

    private void saveAllFeatures(CanyonEntity canyonEntity) {
        canyonDescentEntityRepository.saveAll(canyonEntity.getCanyonDescent());
        canyonDifficultyEntityRepository.saveAll(canyonEntity.getCanyonDifficulty());
        canyonLinkEntityRepository.saveAll(canyonEntity.getCanyonLink());
        canyonProhibitionEntityRepository.saveAll(canyonEntity.getCanyonProhibition());
        canyonRappelingEntityRepository.saveAll(canyonEntity.getCanyonRappeling());
        canyonScheduleEntityRepository.saveAll(canyonEntity.getCanyonSchedule());
        canyonCanyonNearEntityRepository.saveAll(canyonEntity.getCanyonCanyonNear());
    }

    @Override
    public Canyon updateCanyon(Canyon canyon) {
        CanyonEntity canyonEntity = saveSimpleCanyonAndPrepareCanyonEntityFeatures(canyon);
        saveAllFeatures(canyonEntity);
        return canyonRepositoryMapper.canyonEntityToCanyon(canyonEntity);
    }

    private CanyonEntity saveSimpleCanyonAndPrepareCanyonEntityFeatures(Canyon canyon) {
        SimpleCanyonEntity save =
                simpleCanyonEntityRepository.save(
                        simpleCanyonRepositoryMapper.simpleCanyonToSimpleCanyonEntity(canyon));

        Long canyonId = save.getCanyonId();
        canyon.setCanyonId(canyonId);
        CanyonEntity canyonEntity = canyonRepositoryMapper.canyonToCanyonEntity(canyon);

        canyonEntity
                .getCanyonRappeling()
                .forEach(
                        canyonRappeling -> {
                            canyonRappeling.setCanyonId(canyonId);
                            canyonRappeling.setCanyon(
                                    canyonRepositoryMapper.canyonEntityToSimpleCanyonEntity(canyonEntity));
                        });
        canyonEntity
                .getCanyonDescent()
                .forEach(
                        canyonDescent -> {
                            canyonDescent.setCanyonId(canyonId);
                            canyonDescent.setCanyon(
                                    canyonRepositoryMapper.canyonEntityToSimpleCanyonEntity(canyonEntity));
                        });
        canyonEntity
                .getCanyonSchedule()
                .forEach(
                        canyonSchedule -> {
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
        return canyonEntity;
    }
}
