package cn.ripple.controller.common;


import cn.hutool.core.util.StrUtil;
import cn.ripple.common.exception.RippleException;
import cn.ripple.common.limit.RedisRaterLimiter;
import cn.ripple.common.utils.IpInfoUtil;
import cn.ripple.common.utils.QiniuUtil;
import cn.ripple.common.utils.ResultUtil;
import cn.ripple.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;


@Slf4j
@RestController
@Api(description = "文件上传接口")
@RequestMapping("/ripple/upload")
@Transactional
public class UploadController {

    @Autowired
    private RedisRaterLimiter redisRaterLimiter;

    @Autowired
    private QiniuUtil qiniuUtil;

//    @Autowired
//    private IpInfoUtil ipInfoUtil;

    @RequestMapping(value = "/file",method = RequestMethod.POST)
    @ApiOperation(value = "文件上传")
    public String upload(@RequestParam("file") MultipartFile file,
                                 HttpServletRequest request) {

//        // IP限流 在线Demo所需 5分钟限1个请求
//        String token = redisRaterLimiter.acquireTokenFromBucket("upload:"+ipInfoUtil.getIpAddr(request), 1, 300000);
//        if (StrUtil.isBlank(token)) {
//            throw new RippleException("上传那么多干嘛，等等再传吧");
//        }

        String imagePath = null;
        String fileName = qiniuUtil.renamePic(file.getOriginalFilename());
        try {
            FileInputStream inputStream = (FileInputStream) file.getInputStream();
            //上传七牛云服务器
            imagePath= qiniuUtil.qiniuInputStreamUpload(inputStream,fileName);
            if(StrUtil.isBlank(imagePath))
                throw new RippleException("上传失败，请检查七牛云配置");
            if(imagePath.contains("error")){
                throw new RippleException(imagePath);
            }
        } catch (Exception e) {
            log.error(e.toString());
            throw new RippleException(e.toString());
        }

        return imagePath;
    }
}
