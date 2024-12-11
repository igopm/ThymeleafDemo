package org.example;

import lombok.Getter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HtmlGenerator {
    private TemplateEngine templateEngine = null;
    @Getter
    private String htmlPage;
    @Getter
    private Context context = null;

    public HtmlGenerator() {
        // Налаштування Template Resolver
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/"); // Шлях до шаблонів
        templateResolver.setSuffix(".html");      // Розширення файлів
        templateResolver.setTemplateMode(TemplateMode.HTML); // Режим шаблону
        templateResolver.setCharacterEncoding("WINDOWS-1251");
        // Ініціалізація Template Engine
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateResolver.setCacheable(false);
        context = new Context();
    }

    public void renderedHtml(String fileTemplateName) {
        // Рендеринг шаблону
        this.htmlPage = templateEngine.process(fileTemplateName, context);
    }

    public void spoolToOutput() {
        Path path = Paths.get("output.html");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString()))) {
            writer.write(this.getHtmlPage());
            System.out.println("Data successfully spooled to file: file:///" + path.toAbsolutePath().toString().replace("\\","/"));
        } catch (
                IOException e) {
            System.err.println("Error while spooling data to file: " + e.getMessage());
        }
    }
}
