package com.postcare.seguimiento_postoperatorio_ms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "parameters")
public class Parametros {
    @Id
    private String id;

    private List<VitalSign> vitalSigns;
    private List<Symptom> symptoms;


    public static class VitalSign {
        private String name;
        private String unit;
        private Range normalRange;
        private boolean isDefault;
    }

    public static class Symptom {
        private String name;
        private List<Value> values;
        private boolean hasScale;
        private Range scale;
        private boolean isDefault;
    }

    public static class Range {
        private double min;
        private double max;
    }

    public static class Value {
        private String id;
        private String name;
    }
}
