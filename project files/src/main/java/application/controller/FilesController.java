package application.controller;

import application.dao.*;
import application.model.*;
import application.service.FilesStorageService;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin("http://localhost:8080")
//@CrossOrigin("http://localhost:3000")
//@RestController

@Controller

public class FilesController {

    @Autowired
    FilesStorageService storageService;

    @Autowired
    private FelhasznaloDAO felhasznaloDAO;
    @Autowired
    private SzerepDAO szerepDAO;
    @Autowired
    private FelhasznaloSzerepDAO felhasznaloSzerepDAO;
    @Autowired
    private TermekDAO termekDAO;
    @Autowired
    private RendelesDAO rendelesDAO;
    @Autowired
    private ErtekelesDAO ertekelesDAO;



    

    @PostMapping("/upload/{file}")
    public String uploadFile(@PathVariable("file") MultipartFile file, @RequestParam("fname") String fname) {
        String message = "";
        String region="us-east-2";
        String bucketName="meal2door";
        String accessKey="AKIA5MK7V5BXD5BV52Y6";
        String secretKey="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";


        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        final AmazonS3 s3= AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(region).build();
        try {

            File file1=new File(fname);
            String filename="products/"+fname+".png";
            FileOutputStream fos = new FileOutputStream(file1);
            fos.write(file.getBytes());
            s3.putObject(bucketName,filename,file1);
            file1.delete();


           /* byte[] bytes = file.getBytes();
            String insPath = "src/main/resources/static/img/products/" + fname+".png";
            Files.write(Paths.get(insPath), bytes);
            insPath = "target/classes/static/img/products/" + fname+".png";
            Files.write(Paths.get(insPath), bytes);

            System.err.print("Uploaded the file successfully: " + file.getOriginalFilename());*/
        } catch (Exception e) {
            System.err.print("Could not upload the file: " + file.getOriginalFilename() + "!");
        }
        return "redirect:/profil";
    }
    @PostMapping("/uploadProfil/{file}")
    public String uploadProfilFile(@PathVariable("file") MultipartFile file, @RequestParam("fname") String fname) {
        String message = "";
        String region="us-east-2";
        String bucketName="meal2door";
        String accessKey="AKIA5MK7V5BXD5BV52Y6";
        String secretKey="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";


        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        final AmazonS3 s3= AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(region).build();
        try {



            File file1=new File(fname);
            String filename="profile/"+fname+".png";
            FileOutputStream fos = new FileOutputStream(file1);
            fos.write(file.getBytes());
            s3.putObject(bucketName,filename,file1);
            file1.delete();

           /* byte[] bytes = file.getBytes();
            String insPath = "src/main/resources/static/img/profiles/" + fname+".png";
            Files.write(Paths.get(insPath), bytes);
            insPath = "target/classes/static/img/profiles/" + fname+".png";
            Files.write(Paths.get(insPath), bytes);

            System.err.print("Uploaded the file successfully: " + file.getOriginalFilename());*/

        } catch (Exception e) {
            System.err.print("Could not upload the file: " + file.getOriginalFilename() + "!"+e);
        }
        return "redirect:/profil";
    }


    @PostMapping("/saveall")
    public String saveAll() {
        String message = "";
        String region="us-east-2";
        String bucketName="meal2door";
        String accessKey="AKIA5MK7V5BXD5BV52Y6";
        String secretKey="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";
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        final AmazonS3 s3= AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(region).build();
        try {
            List<Felhasznalo> felhasznalok=felhasznaloDAO.saveAll();
            List<FelhasznaloSzerep> felhasznalo_szerepek=felhasznaloSzerepDAO.saveAll();
            List<Rendeles> rendelesek=rendelesDAO.saveAll();
            List<Szerep> szerepek=szerepDAO.saveAll();
            List<Termek> termekek=termekDAO.saveAll();
            List<Ertekeles> ertekelesek=ertekelesDAO.saveAll();
            String[] sor=new String[felhasznalok.size()];

            //felhasznalo
            int i=0;
            for (int j=0;j<sor.length;j++){
                sor[j]="VALUES(";
            }
            for (Felhasznalo felhasznalo:felhasznalok)
            {
                sor[i]+="'"+felhasznalo.getId()+"',";
                sor[i]+="'"+felhasznalo.getNev()+"',";
                sor[i]+="'"+felhasznalo.getEmail()+"',";
                sor[i]+="'"+felhasznalo.getJelszo()+"',";
                sor[i]+="'"+felhasznalo.getTelefonszam()+"',";
                sor[i]+="'"+felhasznalo.getSzuletesiDatum()+"',";
                sor[i]+="'"+felhasznalo.getCim()+"',";
                sor[i]+="'"+felhasznalo.getEgyenleg()+"');\n";
                i++;

            }
            File file = new File("felhasznalo.txt");


            if (!file.exists()) {
                file.createNewFile();
            }


            FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
            for (int j=0;j<sor.length;j++) {
                fw.write(sor[j]);
            }
            fw.close();



            String filename="backups/felhasznalo.txt";

            s3.putObject(bucketName,filename,file);



            //felh_szerepek
            sor=new String[felhasznalo_szerepek.size()];
            i=0;
            for (int j=0;j<sor.length;j++){
                sor[j]="VALUES(";
            }
            for (FelhasznaloSzerep felhasznalo_szerep:felhasznalo_szerepek)
            {
                sor[i]+="'"+felhasznalo_szerep.getFelhasznaloID()+"',";
                sor[i]+="'"+felhasznalo_szerep.getSzerepID()+"');\n";

                i++;

            }
            file = new File("felhasznalo_szerep.txt");


            if (!file.exists()) {
                file.createNewFile();
            }


            fw = new FileWriter(file.getAbsoluteFile(), false);
            for (int j=0;j<sor.length;j++) {
                fw.write(sor[j]);
            }
            fw.close();

            filename="backups/felhasznalo_szerep.txt";

            s3.putObject(bucketName,filename,file);

            //rendelesek
            sor=new String[rendelesek.size()];
            i=0;
            for (int j=0;j<sor.length;j++){
                sor[j]="VALUES(";
            }
            for (Rendeles rendeles:rendelesek)
            {
                sor[i]+="'"+rendeles.getId()+"',";
                sor[i]+="'"+rendeles.getNev()+"',";
                sor[i]+="'"+rendeles.getAr()+"',";
                sor[i]+="'"+rendeles.getTermekID()+"',";
                sor[i]+="'"+rendeles.getFelhasznalo_id()+"');\n";

                i++;

            }
            file = new File("rendeles.txt");


            if (!file.exists()) {
                file.createNewFile();
            }


            fw = new FileWriter(file.getAbsoluteFile(), false);
            for (int j=0;j<sor.length;j++) {
                fw.write(sor[j]);
            }
            fw.close();

            filename="backups/rendeles.txt";

            s3.putObject(bucketName,filename,file);

            //szerepek
            sor=new String[szerepek.size()];
            i=0;
            for (int j=0;j<sor.length;j++){
                sor[j]="VALUES(";
            }
            for (Szerep szerep:szerepek)
            {
                sor[i]+="'"+szerep.getId()+"',";

                sor[i]+="'"+szerep.getNev()+"');\n";

                i++;

            }
            file = new File("szerep.txt");


            if (!file.exists()) {
                file.createNewFile();
            }


            fw = new FileWriter(file.getAbsoluteFile(), false);
            for (int j=0;j<sor.length;j++) {
                fw.write(sor[j]);
            }
            fw.close();

            filename="backups/szerep.txt";

            s3.putObject(bucketName,filename,file);

            //termekek
            sor=new String[termekek.size()];
            i=0;
            for (int j=0;j<sor.length;j++){
                sor[j]="VALUES(";
            }
            for (Termek termek:termekek)
            {
                sor[i]+="'"+termek.getId()+"',";
                sor[i]+="'"+termek.getNev()+"',";
                sor[i]+="'"+termek.getKategoria()+"',";
                sor[i]+="'"+termek.getAr()+"',";

                sor[i]+="'"+termek.getEtteremID()+"');\n";

                i++;

            }
            file = new File("termek.txt");


            if (!file.exists()) {
                file.createNewFile();
            }


            fw = new FileWriter(file.getAbsoluteFile(), false);
            for (int j=0;j<sor.length;j++) {
                fw.write(sor[j]);
            }
            fw.close();

            filename="backups/termek.txt";

            s3.putObject(bucketName,filename,file);

            //ertekelesek
            sor=new String[ertekelesek.size()];
            i=0;
            for (int j=0;j<sor.length;j++){
                sor[j]="VALUES(";
            }
            for (Ertekeles ertekeles:ertekelesek)
            {
                sor[i]+="'"+ertekeles.getRendelesid()+"',";
                sor[i]+="'"+ertekeles.getTermekid()+"',";
                sor[i]+="'"+ertekeles.getTermeknev()+"',";
                sor[i]+="'"+ertekeles.getFelhasznalo_id()+"',";
                sor[i]+="'"+ertekeles.getCsillagok()+"',";
                sor[i]+="'"+ertekeles.getMegjegyzes()+"',";
                sor[i]+="'"+ertekeles.getEtterem_id()+"',";



                sor[i]+="'"+ertekeles.getFelhasznalo_nev()+"');\n";

                i++;

            }
            file = new File("ertekelesek.txt");


            if (!file.exists()) {
                file.createNewFile();
            }


            fw = new FileWriter(file.getAbsoluteFile(), false);
            for (int j=0;j<sor.length;j++) {
                fw.write(sor[j]);
            }
            fw.close();

            filename="backups/termek.txt";

            s3.putObject(bucketName,filename,file);

            file.delete();

        } catch (Exception ignored) {

        }
        return "redirect:/profil";
    }


    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}