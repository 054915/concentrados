/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.awt.event.KeyEvent;

/**
 *
 * @author DEVELOPER-4
 */
public class FuncionesValidacion {
    public void numbersOnly(KeyEvent evt) {
        if(!Character.isDigit(evt.getKeyChar()))
        {
            evt.consume();
        }
    }
    
    public void wordsOnly(KeyEvent evt)
    {
        if(!Character.isLetter(evt.getKeyChar()) && evt.getKeyChar() != KeyEvent.VK_SPACE)
        {
            evt.consume();
        }
    }
}
