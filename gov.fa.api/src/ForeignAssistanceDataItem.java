
public class ForeignAssistanceDataItem {
	private String _status = "";
	private String _category = "";
	private String _amount = "";
	private String _benefitingLocation = "";
	private String _agencyName = "";
	private String _fiscalYear = "";
	private String _sector = "";
	
	public String Status() { return _status; }
	public String Category() { return _category; }
	public String Amount() { return _amount; }
	public String BenefitingLocation() { return _benefitingLocation; }
	public String AgencyName() { return _agencyName; }
	public String FiscalYear() { return _fiscalYear; }
	public String Sector() { return _sector; }
	
	public ForeignAssistanceDataItem() { }
	
	public ForeignAssistanceDataItem(String status, String cat, String amount, String location, String agency, String year, String sector) {
		this._status = status;
		this._category = cat;
		this._amount = amount;
		this._benefitingLocation = location;
		this._agencyName = agency;
		this._fiscalYear = year;
		this._sector = sector;
	}

}
