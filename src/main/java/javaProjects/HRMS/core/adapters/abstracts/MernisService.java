package javaProjects.HRMS.core.adapters.abstracts;

import java.rmi.RemoteException;


import javaProjects.HRMS.entities.concretes.Users.Candidate;

public interface MernisService {
    public boolean checkIfRealPerson(Candidate candidate) ;

}
