package org.example.controller;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BatchWebController {

    @Autowired
    private JobExplorer jobExplorer;

    @GetMapping("/monitor")
    public String verEstadoBatch() {
        // Obtenemos las últimas ejecuciones del Job
        List<JobExecution> ejecuciones = jobExplorer.getJobExecutions(
                jobExplorer.getJobInstances("jobMinisterio", 0, 10).get(0)
        );

        StringBuilder html = new StringBuilder();
        html.append("<html><head><title>Monitor Ministerio</title>");
        html.append("<style>table { border-collapse: collapse; width: 100%; font-family: Arial; }");
        html.append("th, td { border: 1px solid #dddddd; text-align: left; padding: 8px; }");
        html.append("th { background-color: #4CAF50; color: white; }</style></head><body>");
        html.append("<h1>Panel de Control: Departamento de Misterios</h1>");
        html.append("<table><tr><th>ID Job</th><th>Estado</th><th>Inicio</th><th>Fin</th></tr>");

        for (JobExecution exec : ejecuciones) {
            html.append("<tr>")
                    .append("<td>").append(exec.getId()).append("</td>")
                    .append("<td>").append(exec.getStatus()).append("</td>")
                    .append("<td>").append(exec.getStartTime()).append("</td>")
                    .append("<td>").append(exec.getEndTime()).append("</td>")
                    .append("</tr>");
        }

        html.append("</table></body></html>");
        return html.toString();
    }
}