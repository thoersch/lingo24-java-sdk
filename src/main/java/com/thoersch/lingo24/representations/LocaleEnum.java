package com.thoersch.lingo24.representations;

public enum LocaleEnum {
    deDE("de-DE", "de", "DE",  55),
    enGB("en-GB", "en", "GB", 97),
    enUS("en-US", "en", "US",47),
    esES("es-ES", "es", "ES", 56),
    esMX("es-MX", "es", "MX", 183),
    frCA("fr-CA", "fr", "CA", 149),
    frFR("fr-FR", "fr", "FR", 58),
    itIT("it-IT", "it", "IT", 67),
    jaJP("ja-JP", "ja", "JP", 64),
    msMY("ms-MY", "ml", "IN", 21),
    plPL("pl-PL", "pl", "PL", 75),
    ptBR("pt-BR", "pt", "BR", 133),
    ruRU("ru-RU", "ru", "RU", 77),
    svSE("sv-SE", "sv", "SE", 86),
    viVN("vi-VN", "vi", "VN", 170),
    zhCN("zh-CN", "zh", "CH", 59),
    zhHANT("zh-HANT", "zh", "HK", 66);

    LocaleEnum(String code, String language, String country, long localeId) {
        this.code = code;
        this.language = language;
        this.country = country;
        this.localeId = localeId;
    }

    private String code;
    private String language;
    private String country;
    private long localeId;

    public String getCode() {
        return code;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public long getLocaleId() {
        return localeId;
    }

    public static LocaleEnum getFromCode(String code){
        for(LocaleEnum locale : LocaleEnum.values()) {
            if (locale.getCode().toLowerCase().equals(code.toLowerCase())) {
                return locale;
            }
        }

        return LocaleEnum.enUS;
    }
}
