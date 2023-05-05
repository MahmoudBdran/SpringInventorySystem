package com.bdran.InventorySystem.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.bdran.InventorySystem.model.Images;
import com.bdran.InventorySystem.repository.UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/testImageUpload")
public class ImagesController {

    @Autowired
    private UploadRepository uploadRepo;

    @GetMapping("/")
    public String index(Model m) {

        List<Images> list = uploadRepo.findAll();

        m.addAttribute("list", list);

        return "addingImages";
    }

//    @PostMapping("/imageUpload")
//    public String imageUpload(@RequestParam MultipartFile img, HttpSession session) {
//
//        Images im = new Images();
//        im.setImageName(img.getOriginalFilename());
//
//        Images uploadImg = uploadRepo.save(im);
//
//        if (uploadImg != null) {
//            try {
//
//                File saveFile = new ClassPathResource("static/img").getFile();
//                System.out.println("----------------------------------");
//                System.out.println(saveFile.getAbsolutePath()+"\\"+ img.getOriginalFilename());
//
//                System.out.println("----------------------------------");
//                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + img.getOriginalFilename());
//                System.out.println(path);
//                Files.copy(img.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
////                String filePath = "C:\\Users\\utd\\AppData\\Local\\Temp\\";
////
////                File file = new File(filePath);
////
////                if (file.exists()) {
////                    file.delete();
////                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        session.setAttribute("msg", "Image Upload Sucessfully");
//
//        return "redirect:/testImageUpload/";
//    }

}
