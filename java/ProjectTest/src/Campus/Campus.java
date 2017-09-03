package Campus;

/** 
 * 
 * @author zhang
 * @Date  2016年9月5日 上午9:17:36
 * @doing 
 */

public class Campus {
	
	private String title;//
	private String officialname;//
	private String city;//recruitcity
	private String specialtips;//标签
	private String loc;//url  applyUrl
	private String description;
	private String source;//来源
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOfficialname() {
		return officialname;
	}
	public void setOfficialname(String officialname) {
		this.officialname = officialname;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSpecialtips() {
		return specialtips;
	}
	public void setSpecialtips(String specialtips) {
		this.specialtips = specialtips;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	@Override
	public String toString() {
		return "Campus [title=" + title + ", officialname=" + officialname + ", city=" + city + ", specialtips="
				+ specialtips + ", loc=" + loc + ", description=" + description + ", source=" + source + "]";
	}
	
	
}
