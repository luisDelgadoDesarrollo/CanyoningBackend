package es.luis.canyoningApp.infrastructure.mapper;

import es.luis.canyoningApp.domain.model.Canyon;
import es.luis.canyoningApp.domain.model.CanyonCanyonNear;
import es.luis.canyoningApp.domain.model.CanyonDescent;
import es.luis.canyoningApp.domain.model.CanyonDifficulty;
import es.luis.canyoningApp.domain.model.CanyonLink;
import es.luis.canyoningApp.domain.model.CanyonProhibition;
import es.luis.canyoningApp.domain.model.CanyonRappeling;
import es.luis.canyoningApp.domain.model.CanyonSchedule;
import es.luis.canyoningApp.domain.model.SimpleCanyon;
import es.luis.canyoningApp.infrastructure.entity.CanyonCanyonNearEntity;
import es.luis.canyoningApp.infrastructure.entity.CanyonDescentEntity;
import es.luis.canyoningApp.infrastructure.entity.CanyonDifficultyEntity;
import es.luis.canyoningApp.infrastructure.entity.CanyonEntity;
import es.luis.canyoningApp.infrastructure.entity.CanyonLinkEntity;
import es.luis.canyoningApp.infrastructure.entity.CanyonProhibitionEntity;
import es.luis.canyoningApp.infrastructure.entity.CanyonRappelingEntity;
import es.luis.canyoningApp.infrastructure.entity.CanyonScheduleEntity;
import es.luis.canyoningApp.infrastructure.entity.SimpleCanyonEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-02T13:04:45+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Amazon.com Inc.)"
)
@Component
public class CanyonRepositoryMapperImpl implements CanyonRepositoryMapper {

    @Override
    public Canyon canyonEntityToCanyon(CanyonEntity canyonEntity) {
        if ( canyonEntity == null ) {
            return null;
        }

        Canyon.CanyonBuilder<?, ?> canyon = Canyon.builder();

        canyon.canyonId( canyonEntity.getCanyonId() );
        canyon.name( canyonEntity.getName() );
        canyon.croquis( canyonEntity.getCroquis() );
        canyon.description( canyonEntity.getDescription() );
        canyon.season( canyonEntity.getSeason() );
        canyon.river( canyonEntity.getRiver() );
        canyon.population( canyonEntity.getPopulation() );
        canyon.access( canyonEntity.getAccess() );
        canyon.approach( canyonEntity.getApproach() );
        canyon.descent( canyonEntity.getDescent() );
        canyon._return( canyonEntity.get_return() );
        canyon.scape( canyonEntity.getScape() );
        canyon.deleteAt( canyonEntity.getDeleteAt() );
        canyon.canyonRappeling( canyonRappelingEntityListToCanyonRappelingList( canyonEntity.getCanyonRappeling() ) );
        canyon.canyonDescent( canyonDescentEntityListToCanyonDescentList( canyonEntity.getCanyonDescent() ) );
        canyon.canyonSchedule( canyonScheduleEntityListToCanyonScheduleList( canyonEntity.getCanyonSchedule() ) );
        canyon.canyonLink( canyonLinkEntityListToCanyonLinkList( canyonEntity.getCanyonLink() ) );
        canyon.canyonProhibition( canyonProhibitionEntityListToCanyonProhibitionList( canyonEntity.getCanyonProhibition() ) );
        canyon.canyonDifficulty( canyonDifficultyEntityListToCanyonDifficultyList( canyonEntity.getCanyonDifficulty() ) );
        canyon.canyonCanyonNear( canyonCanyonNearEntityListToCanyonCanyonNearList( canyonEntity.getCanyonCanyonNear() ) );

        return canyon.build();
    }

    @Override
    public CanyonRappeling canyonRappelingEntityToCanyonRappeling(CanyonRappelingEntity canyonRappelingEntity) {
        if ( canyonRappelingEntity == null ) {
            return null;
        }

        CanyonRappeling.CanyonRappelingBuilder canyonRappeling = CanyonRappeling.builder();

        canyonRappeling.canyonId( canyonRappelingEntity.getCanyonId() );
        canyonRappeling.descentNumber( canyonRappelingEntity.getDescentNumber() );
        canyonRappeling.step( canyonRappelingEntity.getStep() );
        canyonRappeling.stepType( canyonRappelingEntity.getStepType() );
        canyonRappeling.length( canyonRappelingEntity.getLength() );
        canyonRappeling.location( canyonRappelingEntity.getLocation() );
        canyonRappeling.description( canyonRappelingEntity.getDescription() );

        return canyonRappeling.build();
    }

    @Override
    public CanyonRappelingEntity canyonRappelingToCanyonRappelingEntity(CanyonRappeling canyonRappeling) {
        if ( canyonRappeling == null ) {
            return null;
        }

        CanyonRappelingEntity.CanyonRappelingEntityBuilder canyonRappelingEntity = CanyonRappelingEntity.builder();

        canyonRappelingEntity.canyonId( canyonRappeling.getCanyonId() );
        canyonRappelingEntity.descentNumber( canyonRappeling.getDescentNumber() );
        canyonRappelingEntity.step( canyonRappeling.getStep() );
        canyonRappelingEntity.stepType( canyonRappeling.getStepType() );
        canyonRappelingEntity.length( canyonRappeling.getLength() );
        canyonRappelingEntity.location( canyonRappeling.getLocation() );
        canyonRappelingEntity.description( canyonRappeling.getDescription() );

        return canyonRappelingEntity.build();
    }

    @Override
    public CanyonDescent canyonDescentEntityToCanyonDescent(CanyonDescentEntity canyonDescentEntity) {
        if ( canyonDescentEntity == null ) {
            return null;
        }

        CanyonDescent.CanyonDescentBuilder canyonDescent = CanyonDescent.builder();

        canyonDescent.canyonId( canyonDescentEntity.getCanyonId() );
        canyonDescent.descentNumber( canyonDescentEntity.getDescentNumber() );
        canyonDescent.length( canyonDescentEntity.getLength() );
        canyonDescent.slope( canyonDescentEntity.getSlope() );
        canyonDescent.rapelNum( canyonDescentEntity.getRapelNum() );
        canyonDescent.maxLength( canyonDescentEntity.getMaxLength() );
        canyonDescent.equipment( canyonDescentEntity.getEquipment() );

        return canyonDescent.build();
    }

    @Override
    public CanyonDescentEntity canyonDescentToCanyonDescentEntity(CanyonDescent canyonDescent) {
        if ( canyonDescent == null ) {
            return null;
        }

        CanyonDescentEntity.CanyonDescentEntityBuilder canyonDescentEntity = CanyonDescentEntity.builder();

        canyonDescentEntity.canyonId( canyonDescent.getCanyonId() );
        canyonDescentEntity.descentNumber( canyonDescent.getDescentNumber() );
        canyonDescentEntity.length( canyonDescent.getLength() );
        canyonDescentEntity.slope( canyonDescent.getSlope() );
        canyonDescentEntity.rapelNum( canyonDescent.getRapelNum() );
        canyonDescentEntity.maxLength( canyonDescent.getMaxLength() );
        canyonDescentEntity.equipment( canyonDescent.getEquipment() );

        return canyonDescentEntity.build();
    }

    @Override
    public CanyonSchedule canyonScheduleEntityToCanyonSchedule(CanyonScheduleEntity canyonScheduleEntity) {
        if ( canyonScheduleEntity == null ) {
            return null;
        }

        CanyonSchedule.CanyonScheduleBuilder canyonSchedule = CanyonSchedule.builder();

        canyonSchedule.canyonId( canyonScheduleEntity.getCanyonId() );
        canyonSchedule.car( canyonScheduleEntity.getCar() );
        canyonSchedule.descentNumber( canyonScheduleEntity.getDescentNumber() );
        canyonSchedule.approach( canyonScheduleEntity.getApproach() );
        canyonSchedule.descent( canyonScheduleEntity.getDescent() );
        canyonSchedule._return( canyonScheduleEntity.get_return() );

        return canyonSchedule.build();
    }

    @Override
    public CanyonScheduleEntity canyonScheduleToCanyonScheduleEntity(CanyonSchedule canyonSchedule) {
        if ( canyonSchedule == null ) {
            return null;
        }

        CanyonScheduleEntity.CanyonScheduleEntityBuilder canyonScheduleEntity = CanyonScheduleEntity.builder();

        canyonScheduleEntity.canyonId( canyonSchedule.getCanyonId() );
        canyonScheduleEntity.car( canyonSchedule.getCar() );
        canyonScheduleEntity.descentNumber( canyonSchedule.getDescentNumber() );
        canyonScheduleEntity.approach( canyonSchedule.getApproach() );
        canyonScheduleEntity.descent( canyonSchedule.getDescent() );
        canyonScheduleEntity._return( canyonSchedule.get_return() );

        return canyonScheduleEntity.build();
    }

    @Override
    public CanyonDifficulty canyonDifficultyEntityToCanyonDifficulty(CanyonDifficultyEntity canyonDifficultyEntity) {
        if ( canyonDifficultyEntity == null ) {
            return null;
        }

        CanyonDifficulty.CanyonDifficultyBuilder canyonDifficulty = CanyonDifficulty.builder();

        canyonDifficulty.canyonId( canyonDifficultyEntity.getCanyonId() );
        canyonDifficulty.descentNumber( canyonDifficultyEntity.getDescentNumber() );
        canyonDifficulty.difficultyDesc( canyonDifficultyEntity.getDifficultyDesc() );

        return canyonDifficulty.build();
    }

    @Override
    public CanyonDifficultyEntity canyonDifficultyToCanyonDifficultyEntity(CanyonDifficulty canyonDifficulty) {
        if ( canyonDifficulty == null ) {
            return null;
        }

        CanyonDifficultyEntity.CanyonDifficultyEntityBuilder canyonDifficultyEntity = CanyonDifficultyEntity.builder();

        canyonDifficultyEntity.canyonId( canyonDifficulty.getCanyonId() );
        canyonDifficultyEntity.descentNumber( canyonDifficulty.getDescentNumber() );
        canyonDifficultyEntity.difficultyDesc( canyonDifficulty.getDifficultyDesc() );

        return canyonDifficultyEntity.build();
    }

    @Override
    public CanyonProhibition canyonProhibitionEntityToCanyonProhibition(CanyonProhibitionEntity canyonProhibitionEntity) {
        if ( canyonProhibitionEntity == null ) {
            return null;
        }

        CanyonProhibition.CanyonProhibitionBuilder canyonProhibition = CanyonProhibition.builder();

        canyonProhibition.canyonId( canyonProhibitionEntityCanyonCanyonId( canyonProhibitionEntity ) );
        canyonProhibition.canyonProhibitionId( canyonProhibitionEntity.getCanyonProhibitionId() );
        canyonProhibition.description( canyonProhibitionEntity.getDescription() );

        return canyonProhibition.build();
    }

    @Override
    public CanyonProhibitionEntity canyonProhibitionEntityToCanyonProhibition(CanyonProhibition canyonProhibition) {
        if ( canyonProhibition == null ) {
            return null;
        }

        CanyonProhibitionEntity canyonProhibitionEntity = new CanyonProhibitionEntity();

        canyonProhibitionEntity.setCanyonProhibitionId( canyonProhibition.getCanyonProhibitionId() );
        canyonProhibitionEntity.setDescription( canyonProhibition.getDescription() );

        return canyonProhibitionEntity;
    }

    @Override
    public CanyonLink canyonLinkEntityToCanyonLink(CanyonLinkEntity canyonLinkEntity) {
        if ( canyonLinkEntity == null ) {
            return null;
        }

        CanyonLink.CanyonLinkBuilder canyonLink = CanyonLink.builder();

        canyonLink.canyonId( canyonLinkEntityCanyonCanyonId( canyonLinkEntity ) );
        canyonLink.canyonLinkId( canyonLinkEntity.getCanyonLinkId() );
        canyonLink.link( canyonLinkEntity.getLink() );
        canyonLink.title( canyonLinkEntity.getTitle() );

        return canyonLink.build();
    }

    @Override
    public CanyonLinkEntity canyonLinkToCanyonLinkEntity(CanyonLink canyonLink) {
        if ( canyonLink == null ) {
            return null;
        }

        CanyonLinkEntity canyonLinkEntity = new CanyonLinkEntity();

        canyonLinkEntity.setCanyonLinkId( canyonLink.getCanyonLinkId() );
        canyonLinkEntity.setLink( canyonLink.getLink() );
        canyonLinkEntity.setTitle( canyonLink.getTitle() );

        return canyonLinkEntity;
    }

    @Override
    public CanyonCanyonNear canyonCanyonNearEntityToCanyonCanyonNear(CanyonCanyonNearEntity canyonCanyonNearEntity) {
        if ( canyonCanyonNearEntity == null ) {
            return null;
        }

        CanyonCanyonNear.CanyonCanyonNearBuilder canyonCanyonNear = CanyonCanyonNear.builder();

        canyonCanyonNear.principalCanyon( canyonCanyonNearEntity.getPrincipalCanyon() );
        canyonCanyonNear.nearCanyon( canyonCanyonNearEntity.getNearCanyon() );

        return canyonCanyonNear.build();
    }

    @Override
    public CanyonCanyonNearEntity canyonCanyonNearToCanyonCanyonNearEntity(CanyonCanyonNear canyonCanyonNear) {
        if ( canyonCanyonNear == null ) {
            return null;
        }

        CanyonCanyonNearEntity.CanyonCanyonNearEntityBuilder canyonCanyonNearEntity = CanyonCanyonNearEntity.builder();

        canyonCanyonNearEntity.principalCanyon( canyonCanyonNear.getPrincipalCanyon() );
        canyonCanyonNearEntity.nearCanyon( canyonCanyonNear.getNearCanyon() );

        return canyonCanyonNearEntity.build();
    }

    @Override
    public SimpleCanyonEntity simpleCanyonToSimpleCanyonEntity(SimpleCanyon simpleCanyon) {
        if ( simpleCanyon == null ) {
            return null;
        }

        SimpleCanyonEntity simpleCanyonEntity = new SimpleCanyonEntity();

        simpleCanyonEntity.setCanyonId( simpleCanyon.getCanyonId() );
        simpleCanyonEntity.setName( simpleCanyon.getName() );
        simpleCanyonEntity.setSeason( simpleCanyon.getSeason() );
        simpleCanyonEntity.setDescription( simpleCanyon.getDescription() );
        simpleCanyonEntity.setCroquis( simpleCanyon.getCroquis() );
        simpleCanyonEntity.setRiver( simpleCanyon.getRiver() );
        simpleCanyonEntity.setPopulation( simpleCanyon.getPopulation() );
        simpleCanyonEntity.setAccess( simpleCanyon.getAccess() );
        simpleCanyonEntity.setApproach( simpleCanyon.getApproach() );
        simpleCanyonEntity.setDescent( simpleCanyon.getDescent() );
        simpleCanyonEntity.set_return( simpleCanyon.get_return() );
        simpleCanyonEntity.setScape( simpleCanyon.getScape() );
        simpleCanyonEntity.setDeleteAt( simpleCanyon.getDeleteAt() );

        return simpleCanyonEntity;
    }

    @Override
    public CanyonEntity canyonToCanyonEntity(Canyon canyon) {
        if ( canyon == null ) {
            return null;
        }

        CanyonEntity canyonEntity = new CanyonEntity();

        canyonEntity.setCanyonId( canyon.getCanyonId() );
        canyonEntity.setName( canyon.getName() );
        canyonEntity.setSeason( canyon.getSeason() );
        canyonEntity.setDescription( canyon.getDescription() );
        canyonEntity.setCroquis( canyon.getCroquis() );
        canyonEntity.setRiver( canyon.getRiver() );
        canyonEntity.setPopulation( canyon.getPopulation() );
        canyonEntity.setAccess( canyon.getAccess() );
        canyonEntity.setApproach( canyon.getApproach() );
        canyonEntity.setDescent( canyon.getDescent() );
        canyonEntity.set_return( canyon.get_return() );
        canyonEntity.setScape( canyon.getScape() );
        canyonEntity.setDeleteAt( canyon.getDeleteAt() );
        canyonEntity.setCanyonRappeling( canyonRappelingListToCanyonRappelingEntityList( canyon.getCanyonRappeling() ) );
        canyonEntity.setCanyonDescent( canyonDescentListToCanyonDescentEntityList( canyon.getCanyonDescent() ) );
        canyonEntity.setCanyonSchedule( canyonScheduleListToCanyonScheduleEntityList( canyon.getCanyonSchedule() ) );
        canyonEntity.setCanyonLink( canyonLinkListToCanyonLinkEntityList( canyon.getCanyonLink() ) );
        canyonEntity.setCanyonProhibition( canyonProhibitionListToCanyonProhibitionEntityList( canyon.getCanyonProhibition() ) );
        canyonEntity.setCanyonDifficulty( canyonDifficultyListToCanyonDifficultyEntityList( canyon.getCanyonDifficulty() ) );
        canyonEntity.setCanyonCanyonNear( canyonCanyonNearListToCanyonCanyonNearEntityList( canyon.getCanyonCanyonNear() ) );

        return canyonEntity;
    }

    @Override
    public SimpleCanyonEntity canyonEntityToSimpleCanyonEntity(CanyonEntity canyonEntity) {
        if ( canyonEntity == null ) {
            return null;
        }

        SimpleCanyonEntity simpleCanyonEntity = new SimpleCanyonEntity();

        simpleCanyonEntity.setCanyonId( canyonEntity.getCanyonId() );
        simpleCanyonEntity.setName( canyonEntity.getName() );
        simpleCanyonEntity.setSeason( canyonEntity.getSeason() );
        simpleCanyonEntity.setDescription( canyonEntity.getDescription() );
        simpleCanyonEntity.setCroquis( canyonEntity.getCroquis() );
        simpleCanyonEntity.setRiver( canyonEntity.getRiver() );
        simpleCanyonEntity.setPopulation( canyonEntity.getPopulation() );
        simpleCanyonEntity.setAccess( canyonEntity.getAccess() );
        simpleCanyonEntity.setApproach( canyonEntity.getApproach() );
        simpleCanyonEntity.setDescent( canyonEntity.getDescent() );
        simpleCanyonEntity.set_return( canyonEntity.get_return() );
        simpleCanyonEntity.setScape( canyonEntity.getScape() );
        simpleCanyonEntity.setDeleteAt( canyonEntity.getDeleteAt() );

        return simpleCanyonEntity;
    }

    protected List<CanyonRappeling> canyonRappelingEntityListToCanyonRappelingList(List<CanyonRappelingEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<CanyonRappeling> list1 = new ArrayList<CanyonRappeling>( list.size() );
        for ( CanyonRappelingEntity canyonRappelingEntity : list ) {
            list1.add( canyonRappelingEntityToCanyonRappeling( canyonRappelingEntity ) );
        }

        return list1;
    }

    protected List<CanyonDescent> canyonDescentEntityListToCanyonDescentList(List<CanyonDescentEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<CanyonDescent> list1 = new ArrayList<CanyonDescent>( list.size() );
        for ( CanyonDescentEntity canyonDescentEntity : list ) {
            list1.add( canyonDescentEntityToCanyonDescent( canyonDescentEntity ) );
        }

        return list1;
    }

    protected List<CanyonSchedule> canyonScheduleEntityListToCanyonScheduleList(List<CanyonScheduleEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<CanyonSchedule> list1 = new ArrayList<CanyonSchedule>( list.size() );
        for ( CanyonScheduleEntity canyonScheduleEntity : list ) {
            list1.add( canyonScheduleEntityToCanyonSchedule( canyonScheduleEntity ) );
        }

        return list1;
    }

    protected List<CanyonLink> canyonLinkEntityListToCanyonLinkList(List<CanyonLinkEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<CanyonLink> list1 = new ArrayList<CanyonLink>( list.size() );
        for ( CanyonLinkEntity canyonLinkEntity : list ) {
            list1.add( canyonLinkEntityToCanyonLink( canyonLinkEntity ) );
        }

        return list1;
    }

    protected List<CanyonProhibition> canyonProhibitionEntityListToCanyonProhibitionList(List<CanyonProhibitionEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<CanyonProhibition> list1 = new ArrayList<CanyonProhibition>( list.size() );
        for ( CanyonProhibitionEntity canyonProhibitionEntity : list ) {
            list1.add( canyonProhibitionEntityToCanyonProhibition( canyonProhibitionEntity ) );
        }

        return list1;
    }

    protected List<CanyonDifficulty> canyonDifficultyEntityListToCanyonDifficultyList(List<CanyonDifficultyEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<CanyonDifficulty> list1 = new ArrayList<CanyonDifficulty>( list.size() );
        for ( CanyonDifficultyEntity canyonDifficultyEntity : list ) {
            list1.add( canyonDifficultyEntityToCanyonDifficulty( canyonDifficultyEntity ) );
        }

        return list1;
    }

    protected List<CanyonCanyonNear> canyonCanyonNearEntityListToCanyonCanyonNearList(List<CanyonCanyonNearEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<CanyonCanyonNear> list1 = new ArrayList<CanyonCanyonNear>( list.size() );
        for ( CanyonCanyonNearEntity canyonCanyonNearEntity : list ) {
            list1.add( canyonCanyonNearEntityToCanyonCanyonNear( canyonCanyonNearEntity ) );
        }

        return list1;
    }

    private Long canyonProhibitionEntityCanyonCanyonId(CanyonProhibitionEntity canyonProhibitionEntity) {
        if ( canyonProhibitionEntity == null ) {
            return null;
        }
        SimpleCanyonEntity canyon = canyonProhibitionEntity.getCanyon();
        if ( canyon == null ) {
            return null;
        }
        Long canyonId = canyon.getCanyonId();
        if ( canyonId == null ) {
            return null;
        }
        return canyonId;
    }

    private Long canyonLinkEntityCanyonCanyonId(CanyonLinkEntity canyonLinkEntity) {
        if ( canyonLinkEntity == null ) {
            return null;
        }
        SimpleCanyonEntity canyon = canyonLinkEntity.getCanyon();
        if ( canyon == null ) {
            return null;
        }
        Long canyonId = canyon.getCanyonId();
        if ( canyonId == null ) {
            return null;
        }
        return canyonId;
    }

    protected List<CanyonRappelingEntity> canyonRappelingListToCanyonRappelingEntityList(List<CanyonRappeling> list) {
        if ( list == null ) {
            return null;
        }

        List<CanyonRappelingEntity> list1 = new ArrayList<CanyonRappelingEntity>( list.size() );
        for ( CanyonRappeling canyonRappeling : list ) {
            list1.add( canyonRappelingToCanyonRappelingEntity( canyonRappeling ) );
        }

        return list1;
    }

    protected List<CanyonDescentEntity> canyonDescentListToCanyonDescentEntityList(List<CanyonDescent> list) {
        if ( list == null ) {
            return null;
        }

        List<CanyonDescentEntity> list1 = new ArrayList<CanyonDescentEntity>( list.size() );
        for ( CanyonDescent canyonDescent : list ) {
            list1.add( canyonDescentToCanyonDescentEntity( canyonDescent ) );
        }

        return list1;
    }

    protected List<CanyonScheduleEntity> canyonScheduleListToCanyonScheduleEntityList(List<CanyonSchedule> list) {
        if ( list == null ) {
            return null;
        }

        List<CanyonScheduleEntity> list1 = new ArrayList<CanyonScheduleEntity>( list.size() );
        for ( CanyonSchedule canyonSchedule : list ) {
            list1.add( canyonScheduleToCanyonScheduleEntity( canyonSchedule ) );
        }

        return list1;
    }

    protected List<CanyonLinkEntity> canyonLinkListToCanyonLinkEntityList(List<CanyonLink> list) {
        if ( list == null ) {
            return null;
        }

        List<CanyonLinkEntity> list1 = new ArrayList<CanyonLinkEntity>( list.size() );
        for ( CanyonLink canyonLink : list ) {
            list1.add( canyonLinkToCanyonLinkEntity( canyonLink ) );
        }

        return list1;
    }

    protected List<CanyonProhibitionEntity> canyonProhibitionListToCanyonProhibitionEntityList(List<CanyonProhibition> list) {
        if ( list == null ) {
            return null;
        }

        List<CanyonProhibitionEntity> list1 = new ArrayList<CanyonProhibitionEntity>( list.size() );
        for ( CanyonProhibition canyonProhibition : list ) {
            list1.add( canyonProhibitionEntityToCanyonProhibition( canyonProhibition ) );
        }

        return list1;
    }

    protected List<CanyonDifficultyEntity> canyonDifficultyListToCanyonDifficultyEntityList(List<CanyonDifficulty> list) {
        if ( list == null ) {
            return null;
        }

        List<CanyonDifficultyEntity> list1 = new ArrayList<CanyonDifficultyEntity>( list.size() );
        for ( CanyonDifficulty canyonDifficulty : list ) {
            list1.add( canyonDifficultyToCanyonDifficultyEntity( canyonDifficulty ) );
        }

        return list1;
    }

    protected List<CanyonCanyonNearEntity> canyonCanyonNearListToCanyonCanyonNearEntityList(List<CanyonCanyonNear> list) {
        if ( list == null ) {
            return null;
        }

        List<CanyonCanyonNearEntity> list1 = new ArrayList<CanyonCanyonNearEntity>( list.size() );
        for ( CanyonCanyonNear canyonCanyonNear : list ) {
            list1.add( canyonCanyonNearToCanyonCanyonNearEntity( canyonCanyonNear ) );
        }

        return list1;
    }
}
