package com.opingoo.services;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 * @author Federico Recio
 */
public class OpingooApplicationConfiguration  extends Configuration {


    @JsonProperty("swagger")
    public SwaggerBundleConfiguration swaggerBundleConfiguration;


    @Valid
    @NotNull
    @JsonProperty
    private DataSourceFactory database = new DataSourceFactory();

    public DataSourceFactory getDataSourceFactory(){
        return database;
    }

}
