package com.transformacao.models;

public class Procedure {
    private String name;
    private String rn;
    private String effectiveDate;
    private String dentalCoverage;
    private String outpatientCoverage;
    private String hospitalWithObstetrics;
    private String hospitalWithoutObstetrics;
    private String referencePlan;
    private String highComplexity;
    private String usageGuideline;
    private String subgroup;
    private String group;
    private String chapter;

    public Procedure(String name, String rn, String effectiveDate, String dentalCoverage,
                     String outpatientCoverage, String hospitalWithObstetrics,
                     String hospitalWithoutObstetrics, String referencePlan,
                     String highComplexity, String usageGuideline, String subgroup,
                     String group, String chapter) {
        this.name = name;
        this.rn = rn;
        this.effectiveDate = effectiveDate;
        this.dentalCoverage = dentalCoverage;
        this.outpatientCoverage = outpatientCoverage;
        this.hospitalWithObstetrics = hospitalWithObstetrics;
        this.hospitalWithoutObstetrics = hospitalWithoutObstetrics;
        this.referencePlan = referencePlan;
        this.highComplexity = highComplexity;
        this.usageGuideline = usageGuideline;
        this.subgroup = subgroup;
        this.group = group;
        this.chapter = chapter;
    }

    public String getName() { return name; }
    public String getRn() { return rn; }
    public String getEffectiveDate() { return effectiveDate; }
    public String getDentalCoverage() { return dentalCoverage; }
    public String getOutpatientCoverage() { return outpatientCoverage; }
    public String getHospitalWithObstetrics() { return hospitalWithObstetrics; }
    public String getHospitalWithoutObstetrics() { return hospitalWithoutObstetrics; }
    public String getReferencePlan() { return referencePlan; }
    public String getHighComplexity() { return highComplexity; }
    public String getUsageGuideline() { return usageGuideline; }
    public String getSubgroup() { return subgroup; }
    public String getGroup() { return group; }
    public String getChapter() { return chapter; }
    
}
