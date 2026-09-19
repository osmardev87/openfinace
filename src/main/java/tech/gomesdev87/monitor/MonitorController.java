package tech.gomesdev87.monitor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MonitorController {

    @GetMapping("/api/monitor")
    public Map<String, Object> getSystemStats() {
        Map<String, Object> stats = new HashMap<>();

        // Leitura da Memória RAM (em Megabytes)
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory() / (1024 * 1024); // Limite máximo configurado (-Xmx)
        long totalMemory = runtime.totalMemory() / (1024 * 1024); // Memória alocada pela JVM
        long freeMemory = runtime.freeMemory() / (1024 * 1024); // Memória livre na JVM
        long usedMemory = totalMemory - freeMemory;

        Map<String, Long> ram = new HashMap<>();
        ram.put("usedMB", usedMemory);
        ram.put("limitMB", maxMemory > 0 ? maxMemory : totalMemory);

        // Leitura do Espaço em Disco (Raiz '/')
        File root = new File("/");
        long totalDisk = root.getTotalSpace() / (1024 * 1024 * 1024); // Em GB
        long freeDisk = root.getFreeSpace() / (1024 * 1024 * 1024);   // Em GB
        long usedDisk = totalDisk - freeDisk;

        Map<String, Long> disk = new HashMap<>();
        disk.put("usedGB", usedDisk);
        disk.put("totalGB", totalDisk);

        stats.put("ram", ram);
        stats.put("disk", disk);

        return stats;
    }
}