package application.controller;

import application.dao.*;
import application.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MainController {
    public static final String SHOP_EMAIL_ADDRESS = "meal2doorproject@gmail.com";
    public static final String SHOP_PASSWORD = "etel1234";

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



    @GetMapping(value = "/register")
    public String register(Model model) {
        return "register";
    }

    @PostMapping(value = "/addFelhasznalo")
    public String addFelhasznalo(
            @RequestParam("nev") String nev, @RequestParam("email") String email,
            @RequestParam("jelszo") String jelszo, @RequestParam("telefonszam") String telefonszam,
            @RequestParam("szuletesi_datum") String szuletesiDatumString, @RequestParam("cim") String cim,
            @RequestParam("etterem") boolean etterem,
            RedirectAttributes redirAttrs
    ) throws ParseException {
        try {
            Szerep szerep;
            if (etterem) {
                szerep = new Szerep(2, "ETTEREM");
            } else {
                szerep = new Szerep(3, "MEGRENDELO");
            }

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            felhasznaloDAO.createFelhasznalo(new Felhasznalo(0, nev, email, jelszo, telefonszam, format.parse(szuletesiDatumString), cim, 0, szerep));
        }catch (DuplicateKeyException e){
            redirAttrs.addFlashAttribute("error", "User already exists with this email address!");
            return "redirect:/register";
        }
        return "redirect:/login";
    }

    @GetMapping(value = "/termek/{id}")
    public String termek(Model model,@PathVariable("id") int id)
    {
        Felhasznalo felhasznalo=felhasznaloDAO.getFelhasznaloByID(id);
        model.addAttribute("profil_id", felhasznalo.getId());
        return "termek";
    }





    @PostMapping(value = "/addTermek")
    public String addTermek(
            @RequestParam("nev") String nev,
            @RequestParam("kategoria") String kategoria,
            @RequestParam("ar") int ar,
            @RequestParam("profil_id") int etteremID)
            {
        Felhasznalo felhasznalo= felhasznaloDAO.getFelhasznaloByID(etteremID);
        if (felhasznalo.getSzerep().getId() == 2) {


            termekDAO.insertTermek(new Termek(0, nev, kategoria, ar, etteremID));
        }


        return "redirect:/profil";
    }

    @PostMapping(value = "/ertekel")
    public String ertekel(
            @RequestParam("rendid") int rendid,
            @RequestParam("termid") int termid,
            @RequestParam("termnev") String termnev,
            @RequestParam("felhid") int felhid,
            @RequestParam("csillagok") int csillagok,
            @RequestParam("megjegyzes") String megjegyzes,
            @RequestParam("etterem") int etterem,
            @RequestParam("felhnev") String felhnev,
            RedirectAttributes redirAttrs

            ) {
        try {
            Ertekeles ertekeles = new Ertekeles(rendid, termid, termnev, felhid, csillagok, megjegyzes, etterem, felhnev);
            ertekelesDAO.createErtekeles(ertekeles);
        }
        catch (DuplicateKeyException e){
            redirAttrs.addFlashAttribute("error", "You can only rate an order once!");
            return "redirect:/orders";
        }




        return "redirect:/orders";
    }

    @PostMapping(value = "/updateTelefonszam/{id}")
    public String updateTelefonszam(@PathVariable("id") int id, @RequestParam("telefonszam") String telefonszam) {
        Felhasznalo felhasznalo = felhasznaloDAO.getFelhasznaloByID(id);
        felhasznaloDAO.updateTelefonszam(felhasznalo.getEmail(), telefonszam);

        return "redirect:/profil";
    }

    @PostMapping(value = "/updateNev/{id}")
    public String updateNev(@PathVariable("id") int id, @RequestParam("nev") String nev) {
        Felhasznalo felhasznalo = felhasznaloDAO.getFelhasznaloByID(id);
        felhasznaloDAO.updateNev(felhasznalo.getEmail(), nev);

        return "redirect:/profil";
    }

    @PostMapping(value = "/updateCim/{id}")
    public String updateCim(@PathVariable("id") int id, @RequestParam("cim") String cim) {
        Felhasznalo felhasznalo = felhasznaloDAO.getFelhasznaloByID(id);
        felhasznaloDAO.updateCim(felhasznalo.getEmail(), cim);

        return "redirect:/profil";
    }

    @GetMapping(value = "/fizetes/{id}")
    public String fizetes(@PathVariable("id") int id, Model model) {
        model.addAttribute("id", id);

        return "fizetes";
    }

    @PostMapping(value = "/updateEgyenleg/{id}")
    public String updateEgyenleg(@PathVariable("id") int id,
                                 @RequestParam("amount") String amountString,
                                 @RequestParam("cardnumber") String cnum,
                                 @RequestParam("expmonth") String month ,
                                 @RequestParam("expyear") String year,
                                 @RequestParam("cvv") String cvv,
                                 @RequestParam("cardname") String nev,
                                 RedirectAttributes redirAttrs) {

        Felhasznalo felhasznalo = felhasznaloDAO.getFelhasznaloByID(id);

        try {
                if(nev.length()<5){
                    redirAttrs.addFlashAttribute("error", "Name is too short!");
                    return "redirect:/fizetes/{id}";
                }
                if (cvv.length()!=3){
                    redirAttrs.addFlashAttribute("error", "CVV number must be 3 digit long!");
                    return "redirect:/fizetes/{id}";
                }
                int iyear=Integer.parseInt(year);
                int imonth=0;
                if(month.charAt(0)=='0'){
                    String smonth=Character.toString(month.charAt(1));
                    imonth=Integer.parseInt(smonth);
                }
                else{
                    imonth=Integer.parseInt(month);
                }
                if (Calendar.getInstance().get(Calendar.YEAR)>iyear){
                    redirAttrs.addFlashAttribute("error", "Card expired!");
                    return "redirect:/fizetes/{id}";
                }
                if ((Calendar.getInstance().get(Calendar.MONTH)<=imonth&&Calendar.getInstance().get(Calendar.YEAR)<=iyear)||(Calendar.getInstance().get(Calendar.MONTH)>imonth&&Calendar.getInstance().get(Calendar.YEAR)<iyear)) {


                        cnum=cnum.replace("-","");
                        if (cnum.length() == 16) {
                            int sum = 0;
                            String[] chars = new String[cnum.length()];
                            int[] cnums = new int[cnum.length()];
                            for (int i = 0; i < cnum.length(); i++) {
                                chars[i] = Character.toString(cnum.charAt(i));
                                cnums[i] = Integer.parseInt(chars[i]);
                                if (i % 2 == 0) {
                                    cnums[i] = cnums[i] * 2;
                                    if (cnums[i] > 9) {
                                        cnums[i] = cnums[i] - 9;
                                    }
                                }
                                sum += cnums[i];
                            }
                            if (sum % 10 == 0) {
                                int amount = Integer.parseInt(amountString);
                                felhasznaloDAO.updateEgyenleg(felhasznalo.getEmail(), felhasznalo.getEgyenleg() + amount);
                            } else {
                                redirAttrs.addFlashAttribute("error", "Card number is Invalid!");
                                return "redirect:/fizetes/{id}";
                            }
                        } else {
                            redirAttrs.addFlashAttribute("error", "Card number must be 16 digit long!");
                            return "redirect:/fizetes/{id}";
                        }
                    }
                    else{
                        redirAttrs.addFlashAttribute("error", "Card expired!");
                        return "redirect:/fizetes/{id}";
                    }
                    int test_cvv=Integer.parseInt(cvv);




        } catch (NumberFormatException e) {
            redirAttrs.addFlashAttribute("error", "Use numbers "+e.getMessage());
            return "redirect:/fizetes/{id}";
        }


        return "redirect:/profil";
    }

    @PostMapping(value = "/deleteFelhasznalo/{id}")
    public String deleteFelhasznalo(@PathVariable int id) {
        felhasznaloDAO.deleteFelhasznalo(id);

        return "redirect:/admin";
    }

    @PostMapping(value = "/deleteTermek/{id}")
    public String deleteTermek(@PathVariable int id) {
        termekDAO.deleteTermek(id);

        return "redirect:/profil";
    }

    @GetMapping(value = "/home")
    public String home(Model model) {
        model.addAttribute("ettermek", felhasznaloDAO.listEtterem());
        return "home";
    }

    @GetMapping(value = "/teszt")
    public String teszt(Model model) {

        return "teszt";
    }

    @GetMapping(value = "/orders")
    public String orders(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        model.addAttribute(rendelesDAO.listRendeles(felhasznaloDAO.getFelhasznaloIDByEmail(email)));
        return "orders";
    }
    @GetMapping(value = "/ratings/{id}")
    public String ratings(Model model, @PathVariable("id") int id) {

        model.addAttribute(ertekelesDAO.listErtekeles(id));
        return "ratings";
    }

    @GetMapping(value = "/profil")
    public String profil(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Felhasznalo felhasznalo = felhasznaloDAO.getFelhasznaloByEmail(email);

        model.addAttribute("felhasznalo", felhasznalo);

        return getProfil(felhasznalo, model);

    }

    @GetMapping(value = "/profil/{id}")
    public String profilByID(@PathVariable("id") int id, Model model) {
        Felhasznalo felhasznalo = felhasznaloDAO.getFelhasznaloByID(id);

        model.addAttribute("felhasznalo", felhasznalo);

        return getProfil(felhasznalo, model);
    }

    private String getProfil(Felhasznalo felhasznalo, Model model) {
        if (felhasznalo.getSzerep().getId() == 2) {
            model.addAttribute("termekek", termekDAO.getTermekByEtteremID(felhasznalo.getId()));
        }
        model.addAttribute("id", felhasznalo.getId());
        model.addAttribute("nev", felhasznalo.getNev());
        model.addAttribute("email", felhasznalo.getEmail());
        model.addAttribute("telefonszam", felhasznalo.getTelefonszam());
        model.addAttribute("szuletesi_datum", felhasznalo.getSzuletesiDatum());
        model.addAttribute("cim", felhasznalo.getCim());
        model.addAttribute("egyenleg", felhasznalo.getEgyenleg());

        return "profil";
    }

    @GetMapping(value = "/admin")
    public String admin(Model model) {
        List<Felhasznalo> felhasznaloList = felhasznaloDAO.listFelhasznalo();
        List<FelhasznaloSzerep> felhasznaloSzerepList = felhasznaloSzerepDAO.listFelhasznaloSzerep();

        model.addAttribute("felhasznalok", felhasznaloList);

        return "admin";
    }

    @GetMapping(value = "/etterem/{id}")
    public String etterem(@PathVariable("id") int id, Model model) {
        model.addAttribute("termekek", termekDAO.getTermekByEtteremID(id));
        model.addAttribute("etteremid", id);
        model.addAttribute("etteremnev", felhasznaloDAO.getFelhasznaloByID(id));



        return "etterem";
    }

    @PostMapping(value = "/updateTermekNev/{id}")
    public String updateTermekNev(@PathVariable int id, @RequestParam("nev") String nev) {
        termekDAO.updateNev(id, nev);

        return "redirect:/profil";
    }

    @PostMapping(value = "/updateTermekKategoria/{id}")
    public String updateTermekKategoria(@PathVariable int id, @RequestParam("kategoria") String kategoria) {
        termekDAO.updateKategoria(id, kategoria);

        return "redirect:/profil";
    }

    @PostMapping(value = "/updateTermekAr/{id}")
    public String updateTermekAr(@PathVariable int id, @RequestParam("ar") String arString) {
        try {
            int ar = Integer.parseInt(arString);
            termekDAO.updateAr(id, ar);
        } catch (NumberFormatException ignored) {
        }

        return "redirect:/profil";
    }

    @GetMapping(value = "/kosar")
    public String kosar(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Felhasznalo felhasznalo = felhasznaloDAO.getFelhasznaloByEmail(email);
        model.addAttribute("termekek", ((FelhasznaloDetails) auth.getPrincipal()).getKosar().getTermekek());
        model.addAttribute("osszeg", ((FelhasznaloDetails) auth.getPrincipal()).getKosar().sum());
        model.addAttribute("egyenleg", felhasznalo.getEgyenleg());
        return "kosar";
    }

    @PostMapping(value = "/kosarba/{id}")
    public String kosarba(@PathVariable int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ((FelhasznaloDetails) auth.getPrincipal()).getKosar().getTermekek().add(termekDAO.getTermekByID(id));

        return "redirect:/kosar";
    }

    @PostMapping(value = "/kosarbolTorles/{index}")
    public String kosarbolTorles(@PathVariable int index) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ((FelhasznaloDetails) auth.getPrincipal()).getKosar().getTermekek().remove(index);


        return "redirect:/kosar";
    }
    @GetMapping(value = "/rating/{id}")
    public String rating(@PathVariable int id, Model model){
        Rendeles rendeles=rendelesDAO.getRendelesByID(id);
        Termek termek=termekDAO.getTermekByID(rendeles.getTermekID());
        Felhasznalo felhasznalo=felhasznaloDAO.getFelhasznaloByID(rendeles.getFelhasznalo_id());

        model.addAttribute("rendid",rendeles.getId());
        model.addAttribute("termid",rendeles.getTermekID());
        model.addAttribute("food",rendeles.getNev());
        model.addAttribute("felhid",rendeles.getFelhasznalo_id());
        model.addAttribute("etterem",termek.getEtteremID());
        model.addAttribute("felhnev",felhasznalo.getNev());

        return "rating";
    }



    @PostMapping(value = "/rendeles")
    public String rendeles(RedirectAttributes redirAttrs) throws MessagingException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Felhasznalo felhasznalo = felhasznaloDAO.getFelhasznaloByEmail(email);
        Kosar kosar = ((FelhasznaloDetails)auth.getPrincipal()).getKosar();

        int osszAr = 0;
        for (Termek t : kosar.getTermekek()) {
            osszAr += t.getAr();
        }
        if (osszAr <= felhasznalo.getEgyenleg()) {
            int felhasznalo_id = felhasznalo.getId();
            List<Integer> idHalmaz = new ArrayList<>();
            for(int i = 0; i < kosar.getTermekek().size(); i++){
                idHalmaz.add(kosar.getTermekek().get(i).getEtteremID());

            }
            Map<Integer, List<Termek>> termekeEttermenkent = new HashMap<>();
            for(int id : idHalmaz){
                List<Termek> termekek = new ArrayList<>();
                    for (Termek termek : ((FelhasznaloDetails) auth.getPrincipal()).getKosar().getTermekek()) {
                        //termekDAO.insertRendeles(termek, felhasznalo_id);
                        Integer etteremID = termek.getEtteremID();
                        if(etteremID == id) {
                            termekek.add(termek);
                        }
                    }
                termekeEttermenkent.put(id, termekek);
            }
            for (Termek termek : ((FelhasznaloDetails) auth.getPrincipal()).getKosar().getTermekek()) {
                termekDAO.insertRendeles(termek, felhasznalo_id);

            }

            kosar.getTermekek().clear();
            felhasznalo.setEgyenleg(felhasznalo.getEgyenleg() - osszAr);
            felhasznaloDAO.updateEgyenleg(felhasznalo.getEmail(), felhasznalo.getEgyenleg());
            sendmail("Új rendelés érkezett", "Sikeres rendelés", felhasznalo.getEmail());

            for (Map.Entry<Integer, List<Termek>> entry : termekeEttermenkent.entrySet()) {
                Felhasznalo etterem = felhasznaloDAO.getFelhasznaloByID(entry.getKey());
                StringBuilder termekLista = new StringBuilder();
                for (Termek termek : entry.getValue()) {
                    termekLista.append("<br>");
                    termekLista.append("-");
                    termekLista.append(termek.getNev());

                }
                    sendmail("Új rendelés érkezett", "A " + felhasznalo.getNev() + " felhasználó rendelést adott le a kovetkezo termekekkel:" + termekLista, etterem.getEmail());
            }

        }
        else{
            int maradek=osszAr-felhasznalo.getEgyenleg();
            redirAttrs.addFlashAttribute("error", "It's looks like you don't have enough money. You need an extra of: "+maradek);
            return "redirect:/kosar";
        }

        return "redirect:/kosar";
    }


    private void sendmail(String subject, String body, String to) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SHOP_EMAIL_ADDRESS, SHOP_PASSWORD);
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(SHOP_EMAIL_ADDRESS, false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        msg.setSubject(subject);
        msg.setContent(body, "text/html");
        msg.setSentDate(new Date());


        Transport.send(msg);
    }

}
