package comands;

import lab5.Catalog;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class ReportCommand extends Command<Catalog> {

    @Override
    public void implement(Catalog C) {
        VelocityEngine vE= new VelocityEngine();
        vE.setProperty(RuntimeConstants.RESOURCE_LOADER,"classpath");
        vE.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        vE.init();
        try{

            Template t = vE.getTemplate("vtemplate/template.vm");
            VelocityContext context=new VelocityContext();
            context.put("itemList",C.getItemList());
            StringWriter sw= new StringWriter();
            t.merge(context,sw);
            FileWriter fw=new FileWriter(new File("D:/facultate/E2_java_scripts/GitHub/Lab5/HtmlSaves","index.html"));
            fw.write(sw.toString());
            fw.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
