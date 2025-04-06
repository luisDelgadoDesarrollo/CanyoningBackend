package es.luis.canyoningApp.domain.service;

import es.luis.canyoningApp.common.ApplicationContextHolder;
import es.luis.canyoningApp.common.CanyoningAppConfiguration;
import es.luis.canyoningApp.domain.util.AuthenticatedUserBase;

public abstract class BaseService extends AuthenticatedUserBase {

    CanyoningAppConfiguration getCanyoningAppConfiguration() {
        return ApplicationContextHolder.getInstance().getBean(CanyoningAppConfiguration.class);
    }
}
