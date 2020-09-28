package com.example.demo;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.ResultApi;
import com.google.gson.Gson;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Controller
public class DemoGetApiController {

    @ModelAttribute
    ResultApi init() {
        return new ResultApi();
    }

    @GetMapping("/apitest")
    String readme(Model model) throws IOException {
        return "apiTest";
    }

    @PostMapping("/apitest")
    String search(
            @RequestParam(name = "target", required = false) String target,
            @RequestParam(name = "language", required = false) String language,
            Model model) throws IOException {

        //URLの作成。ポスト通信時に検索ワードとラジオボタンを取得して、URLに格納しています。
        String url = "https://api.github.com/search/repositories?q=" + target + "+" + "language:" + language;

        //HTTP通信に必要な情報を用意しています。最終的に結果が、ResponseBodyに格納されます。
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        Response response = call.execute();
        ResponseBody body = response.body();

        //json形式の情報をjsonschema2pojoで作成したPOJOに格納しています。
        String json = body.string();
        Gson gson = new Gson();
        ResultApi resultApi = gson.fromJson(json, ResultApi.class);

        model.addAttribute("resultApi", resultApi);
        return "apiTestResult";
    }
}