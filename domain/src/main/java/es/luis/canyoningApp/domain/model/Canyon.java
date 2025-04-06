package es.luis.canyoningApp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Canyon extends SimpleCanyon {

    private List<CanyonRappeling> canyonRappeling = new ArrayList<>();

    private List<CanyonDescent> canyonDescent = new ArrayList<>();

    private List<CanyonSchedule> canyonSchedule = new ArrayList<>();

    private List<CanyonLink> canyonLink = new ArrayList<>();

    private List<CanyonProhibition> canyonProhibition = new ArrayList<>();

    private List<CanyonDifficulty> canyonDifficulty = new ArrayList<>();

    private List<CanyonCanyonNear> canyonCanyonNear = new ArrayList<>();

    private List<CanyonControlLevel> canyonControlLevel = new ArrayList<>();
}
