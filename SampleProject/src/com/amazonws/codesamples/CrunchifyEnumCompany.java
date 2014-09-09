package com.amazonws.codesamples;

public enum CrunchifyEnumCompany {
	 
    GOOGLE("G"), YAHOO("Y"), EBAY("E"), PAYPAL("P");
 
    private String companyLetter;
 
    private CrunchifyEnumCompany(String s) {
        companyLetter = s;
    }
 
    public String getCompanyLetter() {
        return companyLetter;
    }
    
    public static void main(String[] args) {
        System.out.println("Get enum value for Comapny 'eBay': "
                + CrunchifyEnumCompany.EBAY.getCompanyLetter());
        if(CrunchifyEnumCompany.EBAY.equals("EBAY")){
        	CrunchifyEnumCompany.EBAY.getCompanyLetter();
        }
    }
}