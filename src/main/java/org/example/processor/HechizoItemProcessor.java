package org.example.processor;

import org.example.model.Hechizo;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class HechizoItemProcessor implements ItemProcessor<Hechizo, Hechizo> {
    @Override
    public Hechizo process(Hechizo item) throws Exception {
        // Lógica de clasificación mágica
        if (item.getPotencia() > 80) {
            item.setNivelPeligro("ALTO (PROHIBIDO)");
        } else {
            item.setNivelPeligro("NORMAL (REGISTRADO)");
        }
        return item;
    }
}