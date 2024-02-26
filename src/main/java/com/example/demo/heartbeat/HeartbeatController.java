package com.example.demo.heartbeat;

import com.example.demo.services.GameCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class HeartbeatController {
    @Autowired
    HeartbeatSensor sensor;
    @GetMapping("/heartbeat")
    int getHeartbeat() {
        return 50;
    }

    @Autowired
    GameCatalog catalog;
    @GetMapping("/gameCatalog")
    Collection<String> gameCatalog() {
        return catalog.getGameIdentifiers();
    }
}
