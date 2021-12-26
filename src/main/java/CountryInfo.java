public class CountryInfo {
    private final String countryName;
    private final String region;
    private final int rank;
    private final double score;
    private final double whiskerHigh, whiskerLow;
    private final double economy;
    private final double family;
    private final double health;
    private final double freedom;
    private final double generosity;
    private final double trust;
    private final double dystopia;

    public CountryInfo(String[] data) {
        this.countryName = data[0];
        this.region = data[1];
        this.rank = Integer.parseInt(data[2]);
        this.score = Double.parseDouble(data[3]);
        this.whiskerHigh = Double.parseDouble(data[4]);
        this.whiskerLow = Double.parseDouble(data[5]);
        this.economy = Double.parseDouble(data[6]);
        this.family = Double.parseDouble(data[7]);
        this.health = Double.parseDouble(data[8]);
        this.freedom = Double.parseDouble(data[9]);
        this.generosity = Double.parseDouble(data[10]);
        this.trust = Double.parseDouble(data[11]);
        this.dystopia = Double.parseDouble(data[12]);
    }

    public String getCountryName() {
        return countryName;
    }

    public String getRegion() {
        return region;
    }

    public int getRank() {
        return rank;
    }

    public double getScore() {
        return score;
    }

    public double getWhiskerHigh() {
        return whiskerHigh;
    }

    public double getWhiskerLow() {
        return whiskerLow;
    }

    public double getEconomy() {
        return economy;
    }

    public double getFamily() {
        return family;
    }

    public double getHealth() {
        return health;
    }

    public double getFreedom() {
        return freedom;
    }

    public double getGenerosity() {
        return generosity;
    }

    public double getTrust() {
        return trust;
    }

    public double getDystopia() {
        return dystopia;
    }
}
