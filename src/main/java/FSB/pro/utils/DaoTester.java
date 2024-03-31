package FSB.pro.utils;

import FSB.pro.DAO.CompanyDAO;
import FSB.pro.DAO.UserDAO;

public class DaoTester {
    public static EntityDao tetst(long userId) {
        UserDAO userDAO = new UserDAO();
        CompanyDAO companyDAO = new CompanyDAO();
        EntityDao entityDao = null;
        if (userDAO.findByIdtest(userId)) {
             entityDao = userDAO;
        } else if (companyDAO.getCompanyByIdTest(userId)) {
            entityDao = companyDAO;
        }
        return entityDao;
    }
}
