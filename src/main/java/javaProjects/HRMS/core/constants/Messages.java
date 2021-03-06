package javaProjects.HRMS.core.constants;


public class Messages {


    public static String JobAdvertDeleted(){return "Job Advertisements deleted";}

    public static String Listed(){return  "Data listed";}

    public static String UserNotFound(){return "User not found";}

    public static String RoleAddedToUser(String role, String email){return "Role "+role+" added to user "+email;}

    public static String added(final String name) {
		return name + " has added.";
	}

	public static String updated(final String name) {
		return name + " has updated.";
	}

	public static String deleted(final String name) {
		return name + " has deleted.";
	}

	public static String notFound(final String name) {
		return name + " not found.";
	}

	public static String awaitingApproval(final String name) {
		return name + " is awaiting approval.";
	}

	public static String alreadyExists(final String name) {
		return name + " already exists.";
	}

	public static String verified(final String name) {
		return name + " has been verified.";
	}

	public static String denied(final String name) {
		return name + " has been denied.";
	}

	public static String notVerified(final String name) {
		return name + " could not be verified.";
	}

	public static String complete(final String name) {
		return name + " is complete.";
	}

	public static String RoleCreated(String name){return "Role "+name+" saved"; }

    public static String SystemEmployeeSaved(String email) {return  "System employee saved :"+email;}

    public static String Applied() {return "Successfully applied to the advertisement";}

	public static String ApplicationDeleted() {return "Application Deleted";}

    public static String AlreadyApplied() {return "There is already an application for this advertisement.";}
}

