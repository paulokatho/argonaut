package br.com.katho.argonaut.fixture;

import br.com.katho.argonaut.dto.StudentDTO;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.time.LocalDate;

public class StudentTemplateLoader implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(StudentDTO.class).addTemplate("valid", new Rule() {{
            add("name", "Caio");
            add("identityNumber", "1234567891");
            add("studentSince", LocalDate.now());
            add("birthDate", LocalDate.now());
            add("cellphone", 123456789L);
            add("email", "email@email.com.br");
            add("postalCode", "16503420");
            add("addressLine", "Rua Kahto, Cafelandia, bairro Metal");
            add("monthlyBill", 199.90);
            add("lastPayDate", LocalDate.now());
        }});

        Fixture.of(StudentDTO.class).addTemplate("valid-entity", new Rule() {{
            add("id", 1L);
            add("name", "Caio");
            add("identityNumber", "1234567891");
            add("studentSince", LocalDate.now());
            add("birthDate", LocalDate.now());
            add("cellphone", 123456789L);
            add("email", "email@email.com.br");
            add("postalCode", "16503420");
            add("addressLine", "Rua Kahto, Cafelandia, bairro Metal");
            add("monthlyBill", 199.90);
            add("lastPayDate", LocalDate.now());
        }});
    }
}
