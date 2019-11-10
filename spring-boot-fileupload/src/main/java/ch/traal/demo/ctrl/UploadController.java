package ch.traal.demo.ctrl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

  
  /* member variables */
  private Map<String, Object> result = new HashMap<>();
  
  
  /* constructors */
  
  
  /* methods */
  /**
   * Receive message
   * 
   * @param file
   * @return
   * @throws IOException
   */
  @RequestMapping("/uploadFile")
  public Map<String, Object> uploadFile(
      @RequestParam("attach")MultipartFile file
  ) throws IOException {
    // File info
    System.out.println("File name = "  + file.getOriginalFilename());
    System.out.println("File type = " + file.getContentType());

    // Save to disk
    // file path example 1) Windows c:/, 3) Mac ~/Documents/
    String filePath = "/tmp/";
    file.transferTo(new File(filePath + file.getOriginalFilename()));
    result.put("Success", true);
    return result;
  }
  
  @RequestMapping("/uploadFiles")
  public Map<String, Object> uploadFiles(
      @RequestParam("attach")MultipartFile[] arrFile
  ) throws IOException {
    // File info
    if (arrFile != null) {
      for(MultipartFile file : arrFile) {
        System.out.println("File name = "  + file.getOriginalFilename());
        System.out.println("File type = " + file.getContentType());
        
        // Save to disk
        // file path example 1) Windows c:/, 3) Mac ~/Documents/
        String filePath = "/tmp/";
        file.transferTo(new File(filePath + file.getOriginalFilename()));
        result.put(file.getOriginalFilename(), true);
      }
    }
    return result;
  }
  
}
