package cn.pzhxy.devicemanager.auth.enums;

import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;

public enum RequestTypeEnum {
    GET(GetMapping.class),
    POST(PostMapping.class),
    PUT(PutMapping.class),
    DELETE(DeleteMapping.class),
    PATCH(PatchMapping.class);

    private final Class<? extends Annotation> mappingClass;

    RequestTypeEnum(Class<? extends Annotation> mappingClass) {
        this.mappingClass = mappingClass;
    }

    public Class<? extends Annotation> getMappingClass() {
        return mappingClass;
    }
}