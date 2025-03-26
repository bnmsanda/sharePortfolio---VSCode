package tp04.metier;

public class ActionException {
    
    public static void throwActionNotFound() throws Exception {
        throw new Exception("L'action n'existe pas dans le portefeuille.");
    }

    public static void throwInsufficientQuantity() throws Exception {
        throw new Exception("Quantité à vendre supérieure à celle détenue.");
    }

    public static void throwInvalidJour() throws Exception {
        throw new Exception("Numéro du jour invalide pour cette année.");
    }

    public static void throwNoValueForAnnee() throws Exception {
        throw new Exception("Aucune valeur trouvée pour l'année.");
    }
}

