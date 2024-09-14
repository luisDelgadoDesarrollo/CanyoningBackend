package es.luis.canyoningApp.infrastructure.mapper;

import es.luis.canyoningApp.domain.model.SimpleCanyon;
import es.luis.canyoningApp.infrastructure.entity.CanyonEntity;
import es.luis.canyoningApp.infrastructure.entity.SimpleCanyonEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-13T16:12:36+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Amazon.com Inc.)"
)
@Component
public class SimpleCanyonRepositoryMapperImpl implements SimpleCanyonRepositoryMapper {

    @Override
    public SimpleCanyon simpleCanyonEntityToSimpleCanyon(SimpleCanyonEntity simpleCanyonEntity) {
        if ( simpleCanyonEntity == null ) {
            return null;
        }

        SimpleCanyon.SimpleCanyonBuilder<?, ?> simpleCanyon = SimpleCanyon.builder();

        simpleCanyon.canyonId( simpleCanyonEntity.getCanyonId() );
        simpleCanyon.name( simpleCanyonEntity.getName() );
        simpleCanyon.croquis( simpleCanyonEntity.getCroquis() );
        simpleCanyon.description( simpleCanyonEntity.getDescription() );
        simpleCanyon.season( simpleCanyonEntity.getSeason() );
        simpleCanyon.river( simpleCanyonEntity.getRiver() );
        simpleCanyon.population( simpleCanyonEntity.getPopulation() );
        simpleCanyon.access( simpleCanyonEntity.getAccess() );
        simpleCanyon.approach( simpleCanyonEntity.getApproach() );
        simpleCanyon.descent( simpleCanyonEntity.getDescent() );
        simpleCanyon._return( simpleCanyonEntity.get_return() );
        simpleCanyon.scape( simpleCanyonEntity.getScape() );
        simpleCanyon.deleteAt( simpleCanyonEntity.getDeleteAt() );

        return simpleCanyon.build();
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
    public CanyonEntity simpleCanyonEntityToCanyonEntity(SimpleCanyonEntity save) {
        if ( save == null ) {
            return null;
        }

        CanyonEntity canyonEntity = new CanyonEntity();

        canyonEntity.setCanyonId( save.getCanyonId() );
        canyonEntity.setName( save.getName() );
        canyonEntity.setSeason( save.getSeason() );
        canyonEntity.setDescription( save.getDescription() );
        canyonEntity.setCroquis( save.getCroquis() );
        canyonEntity.setRiver( save.getRiver() );
        canyonEntity.setPopulation( save.getPopulation() );
        canyonEntity.setAccess( save.getAccess() );
        canyonEntity.setApproach( save.getApproach() );
        canyonEntity.setDescent( save.getDescent() );
        canyonEntity.set_return( save.get_return() );
        canyonEntity.setScape( save.getScape() );
        canyonEntity.setDeleteAt( save.getDeleteAt() );

        return canyonEntity;
    }
}
