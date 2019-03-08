package com.dbc.exert;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Properties {

    @Value("${dbc.root.path}")
    private String rootPath;

    @Value("${dbc.jar.path}")
    private String jarPath;

    @Value("${dbc.package.path}")
    private String packagePath;

}
