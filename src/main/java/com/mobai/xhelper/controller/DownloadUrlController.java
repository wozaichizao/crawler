package com.mobai.xhelper.controller;

import com.mobai.xhelper.util.Monster;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("url")
public class DownloadUrlController {

    @RequestMapping("gakki/{episode}")
    public Object gakki(@PathVariable Integer episode){
        return Monster.getDownLoadUrlByEpisode(Monster.GAKKI,episode);
    }

    @RequestMapping("all/gakki")
    public Object gakkiAll(){
        return Monster.getDownLoadUrl(Monster.GAKKI);
    }

    @RequestMapping("all/tbbt")
    public Object tbbtAll(){
        return Monster.getDownLoadUrlS(Monster.TBBT12,12);
    }
}
