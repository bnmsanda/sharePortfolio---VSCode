package tp04.metier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class PortefeuilleTest {

    private Portefeuille portefeuille;  
    private ActionSimple actionTesla;
    
        @BeforeEach
        void setUp() {
            portefeuille = new Portefeuille();
        actionTesla = new ActionSimple("Tesla"); // Initialisation de l'attribut
        portefeuille.myPortefeuille.put(actionTesla, 10); // Ajout de 10 actions Tesla
    }
        
    
        @Test
        public void testVenteActionReussie() throws Exception {
    
             // 测试成功出售 5 股 Tesla
             portefeuille.vendreAction(actionTesla, 5);
            Assertions.assertEquals(5, portefeuille.myPortefeuille.get(actionTesla));
        }
    
        @Test
        public void testVenteActionToutVendre() throws Exception {
            // 测试成功出售全部 10 股 Tesla
        portefeuille.vendreAction(actionTesla, 10);
        Assertions.assertFalse(portefeuille.myPortefeuille.containsKey(actionTesla));
    }

         @Test
        public void testVenteQuantiteInsuffisante() {
        // 测试出售超过持有数量（异常情况）
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            portefeuille.vendreAction(actionTesla, 15);
        });
        Assertions.assertEquals("Quantité insuffisante pour la vente", exception.getMessage());
    }

        @Test
        public void testVenteActionInexistante() {
        // 测试出售不在持仓中的股票（异常情况）
        ActionSimple actionApple = new ActionSimple("Apple");
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            portefeuille.vendreAction(actionApple, 5);
        });
        Assertions.assertEquals("L'action n'est pas présente dans le portefeuille", exception.getMessage());
    }
}