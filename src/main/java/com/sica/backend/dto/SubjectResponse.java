package com.sica.backend.dto;

import java.util.List;

public record SubjectResponse(
    String id,
    String nombre,
    String codigo,
    String profesorId,
    String profesorNombre,
    List<String> estudiantesIds
) {}
