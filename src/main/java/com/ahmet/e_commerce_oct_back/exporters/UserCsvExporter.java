package com.ahmet.e_commerce_oct_back.exporters;

import com.ahmet.e_commerce_oct_back.entities.AppUser;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserCsvExporter extends AbstractExporterCat{

    public void export(List<AppUser> appUsers, HttpServletResponse response) throws IOException {
        super.setResponseHeader(response,"text/csv",".csv");

        ICsvBeanWriter csvwriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);

        String[] csvHeader = {"User Id", "E-mail", "First Name", "Last Name", "Roles", "Enabled"};
        String[] fieldMapping = {"id", "email", "firstName", "lastName", "roles", "enabled"};
        csvwriter.writeHeader(csvHeader);
        for (AppUser user : appUsers) {
            csvwriter.write(user, fieldMapping);
        }
        csvwriter.close();
    }
}
