package com.dbc.exert;

import com.dbc.exert.model.Trie;
import com.dbc.exert.stock.BalanceExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@SpringBootApplication
public class ExertApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExertApplication.class, args);
    }

    @PostConstruct
    public void init() {
//        initTrie("core");
//        initTrie("op");
//        initTrie("client");
    }

//    private void initTrie(String key) {
//        String path = properties.getRootPath() + MessageFormat.format(properties.getJarPath(), key, key, Objects.equals(key, "core") ? "jar" : "war");
//        String packPath = MessageFormat.format(properties.getPackagePath(), key);
//        ThreadPool.run(() -> {
//            try {
//                URL url = new URL("file:" + path);
//                try (JarFile jarFile = new JarFile(path);
//                     URLClassLoader classLoader = new URLClassLoader(new URL[]{url})) {
//                    Enumeration<JarEntry> entries = jarFile.entries();
//                    while (entries.hasMoreElements()) {
//                        JarEntry jarEntry = entries.nextElement();
//                        String name = jarEntry.getName();
//                        if (name.endsWith(".class") && !name.contains("$")) {
//                            if (StringUtils.startsWithIgnoreCase(name, "WEB-INF/classes/")) {
//                                name = StringUtils.replace(name, "WEB-INF/classes/", "");
//                            }
//                            name = StringUtils.replace(name, "/", ".");
//                            name = StringUtils.replace(name, ".class", "");
//                            if (name.endsWith("Service")) {
//                                Class<?> clz = classLoader.loadClass(name);
//                                Component component = clz.getAnnotation(Component.class);
//                                if (Objects.nonNull(component)) {
//                                    String value = component.value();
//                                    String service = value.substring(0, value.length() - 3);
//                                    String version = value.substring(value.length() - 3, value.length());
//                                    Trie.trie.addWord(service);
//                                }
//                            }
////                            String subName = name.replace("/", ".").substring(0, name.length() - 6);
//
//                        }
//                    }
//                }
//            } catch (IOException | ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        });
//    }

}
