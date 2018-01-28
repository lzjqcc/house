package com.qcc.controller;

import com.qcc.annotation.Cache;
import com.qcc.domain.Account;
import com.qcc.exception.BuisnessException;
import com.qcc.utils.CacheMap;
import com.qcc.utils.CommUtils;
import com.qcc.utils.ImageUtils;
import com.qcc.utils.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Cache(space = "image")
    private CacheMap<List<String>> cacheMap;
    @Autowired
    private ResourceLoader resourceLoader;

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public ResponseVO removeImage(@RequestParam("imageIndex") Integer imageIndex) {
        List<String> list = cacheMap.get(CommUtils.getCurrentAccount().getId() + "");
        if (list.remove(imageIndex)) {
            return CommUtils.buildReponseVo(true, "删除成功", null);
        }
        return CommUtils.buildReponseVo(false, "删除失败", null);
    }

    //http://localhost:8080/image/2/a.jpg
    @RequestMapping(value = "/{accountId}/{fileName:.+}", method = RequestMethod.GET)
    public ResponseEntity watchImage(@PathVariable("accountId") Integer accountId,
                                     @PathVariable("fileName")String fileName) {
       try {
           String path = CommUtils.IMAGE_DIR + "/" + accountId + "/" + fileName;
           return ResponseEntity.ok(resourceLoader.getResource(path));
       }catch (Exception e){
           return ResponseEntity.notFound().build();
       }

    }

    //todo 图片压缩
    @RequestMapping(value = "/uploadPicture", method = RequestMethod.POST)
    public Map<String, Object> uploadArticlePic(@RequestParam(value = "image") MultipartFile uploadFile) throws IOException {
        BufferedInputStream inputStream = null;
        File file = null;
        try {
            Account account = CommUtils.getCurrentAccount();
            inputStream = new BufferedInputStream(uploadFile.getInputStream());
            File dir = getImageDir(account.getId());
            file = new File(dir, System.currentTimeMillis() + "." + uploadFile.getOriginalFilename().split("\\.")[1]);
            ImageUtils.reduceImg(inputStream, file, 0.2f);
            Map<String, Object> map = new HashMap<>();
            map.put("pictureURL", CommUtils.getImageURL(file.getPath()));
            map.put("index", putCache(account.getId(), CommUtils.getImageURL(file.getPath())));
            return map;
        } catch (Exception e) {
            if (file != null) {
                file.deleteOnExit();
                ;
            }
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                CommUtils.closeStream(inputStream);
            }
        }
        return new HashMap<>();
    }

    private Integer  putCache(Integer accountId, String imageURL) {
        List<String> list = cacheMap.get(accountId + "");
        if (CollectionUtils.isEmpty(list)) {
            list = new ArrayList<>();
            list.add(imageURL);
            cacheMap.put(accountId + "", list);
            return 0;
        }
        list.add(imageURL);
        return list.size()-1;
    }

    private File getImageDir(Integer accountId) {
        Resource resource = resourceLoader.getResource("file:src/main/resources/images");
        try {
            File file = Files.createDirectories(Paths.get(resource.getFile().getPath(), accountId + "")).toFile();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
