package Entity;

public class Enrollee extends Company implements Comparable<Enrollee>{

	private String userId;

	private String firstName;

	private String lastName;

	private Integer version;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString()
	{
		String printOut = "";
		if(this.userId != null && this.firstName != null && this.lastName != null && this.version != null && super.getCompany()!= null)
		{
			printOut = "User Id: " + this.userId + ", First Name: " + this.firstName + ", Last Name: " + this.lastName + ", Version: " + this.version.toString() + ", Company: " + super.getCompany();
		}
		else
		{
			printOut = "Problem formatting print out, possibly due to malformed csv file";
		}

		return printOut;
	}

    @Override
    public int compareTo(Enrollee o) {
				Integer result = this.getLastName().compareToIgnoreCase(o.getLastName());
				if(result != 0)
				{
					return result;
				}
        return this.getFirstName().compareToIgnoreCase(o.getFirstName());
    }
}
