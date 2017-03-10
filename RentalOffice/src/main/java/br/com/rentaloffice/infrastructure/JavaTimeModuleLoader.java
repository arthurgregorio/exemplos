package br.com.rentaloffice.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 10/03/2017
 */
@Provider
public class JavaTimeModuleLoader implements ContextResolver<ObjectMapper> {

    private final ObjectMapper mapper;

    /**
     * 
     */
    public JavaTimeModuleLoader() {
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
        this.mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }
    
    /**
     * 
     * @param type
     * @return 
     */
    @Override
    public ObjectMapper getContext(Class<?> type) {
       return this.mapper;
    }
}
