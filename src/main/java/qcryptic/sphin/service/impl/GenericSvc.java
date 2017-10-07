package qcryptic.sphin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.DefaultPropertiesPersister;
import qcryptic.sphin.service.IGenericSvc;

import java.io.*;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Kyle on 10/6/2017.
 */
@Service
public class GenericSvc implements IGenericSvc {

    @Override
    public boolean checkForAdmin() {
        return false;
    }

}
