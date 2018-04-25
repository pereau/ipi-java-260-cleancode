// 1) Noms significatifs

// Le nom des cellules ne donne aucune indication sur ce qu'elles contiennent
// Le nom de la ligne d'entête headerTotal n'indique pas qu'il s'agit d'une ligne
// Le nom des variables mélange français et anglais à la fois dans le vocabulaire et la syntaxe ce qui pourrait s'avérer problématique pour un non francophone qui reprendrait le code

XSSFSheet prixTotal = workbook.createSheet("Prix Total");
Row headerTotal = prixTotal.createRow(0);
Cell cell1 = headerTotal.createCell(0);
cell1.setCellValue("Nombre  total d'articles");
Cell cell2 = headerTotal.createCell(1);
cell2.setCellValue("Prix  total");

Row rowTotal = prixTotal.createRow(1);
Cell cell3 = rowTotal.createCell(0);
cell3.setCellValue(nbArticle);
Cell cell4 = rowTotal.createCell(1);
cell4.setCellValue(total);


// Le nom des cellules correspond à leur contenu
// Le nom de la ligne contient Row qui indique qu'il s'agit d'une ligne.
// Le nom des variable est uniformisé

XSSFSheet totalSheet = workbook.createSheet("Prix Total");
Row totalHeaderRow = totalSheet.createRow(0);
Cell cellHeaderArticle = totalHeaderRow.createCell(0);
cellHeaderArticle.setCellValue("Nombre  total d'articles");
Cell cellHeaderPrice = totalHeaderRow.createCell(1);
cellHeaderPrice.setCellValue("Prix  total");

Row totalRow = totalSheet.createRow(1);
Cell cellArticle = totalRow.createCell(0);
cellArticle.setCellValue(nbArticle);
Cell cellPrice = totalRow.createCell(1);
cellPrice.setCellValue(total);

// 2 factorisation de code

// Factorisation de code
// Pour une même feuille Excel, les opérations de création et de remplissage d'une cellule nécessitent:
// la création d'une ligne, la création d'une cellule et enfin le remplissage de cette cellule.

XSSFWorkbook workbook = new XSSFWorkbook();
XSSFSheet sheet = workbook.createSheet("clients");
Row headerRow = sheet.createRow(0);
Cell cellPrenom = headerRow.createCell(0);
cellPrenom.setCellValue("Prénom");
Cell cellNom = headerRow.createCell(1);
cellNom.setCellValue("Nom");
Cell cellAge = headerRow.createCell(2);
cellAge.setCellValue("Age");
Cell cellCp = headerRow.createCell(3);
cellCp.setCellValue("Code Postal");
Cell cellCommune = headerRow.createCell(4);
cellCommune.setCellValue("Commune");
			
			
		

// On crée une fonction newCell qui réunit les 3 opérations en une seule.
// L'exemple ci dessous ne fonctionne pas réellement car la création d'une ligne ayant le même index qu'une ligne existante l'"écrase".
// Il faudrait en fait vérifier si la ligne existe et dans ce cas récupérer cette ligne au lieu d'en créer une nouvelle.
XSSFWorkbook workbook = new XSSFWorkbook();
XSSFSheet sheet = workbook.createSheet("clients");	
Cell headerCell = newCell(sheet, 0, 0, "Prénom");
headerCell = newCell(sheet, 0, 1, "Nom");
headerCell = newCell(sheet, 0, 2, "Age");
headerCell = newCell(sheet, 0, 3, "Code postal");
headerCell = newCell(sheet, 0, 3, "Commune");


private Cell newCell(XSSFSheet sheet,  int rowIndex, int cellIndex, String cellContent ) {
		Row row = sheet.createRow(rowIndex);
		Cell cell = row.createCell(cellIndex);
		cell.setCellValue(cellContent);
		return cell;
	}
	
//3) Correction des effets secondaires

// Modification de la fonction pour éviter les effets secondaires : on ajoute une condition dans la fonction newCell qui
// récupère la ligne numéro rowIndex si elle existe déjà ou bien crée cette ligne si elle n'existe pas.

private Cell newCell(XSSFSheet sheet,  int rowIndex, int cellIndex, String cellContent ) {
		Row row;
		if (sheet.getRow(rowIndex)!=null) {
			row = sheet.getRow(rowIndex);
		} else {
			row = sheet.createRow(rowIndex);
		}
		Cell cell = row.createCell(cellIndex);
		cell.setCellValue(cellContent);
		return cell;
	}
	
	
// 4) Gestion des erreurs
// Une partie d'un code appel la méthode division de la classe Diviser
// Comme il s'agit d'une division par 0, le résulat génère une exception qui stoppe l'exécution du programme .
Diviser diviser = new DeleteMe();
        	String resultat = diviser.division(10.0, 0.0);
        	System.out.println(resultat);
			
// Classe diviser			
public class Diviser {
	
	public String division (Double numberOne, Double numberTwo) {
		
		Double result = numberOne/numberTwo;
		return result.toString();
			
	}

}

// En ajoutant un block try, catch, on intercepte l'exception et la méthode retourne le message d'erreur "Division par 0". 
// En fait l'exception est déjà prévu par "java" et retourne le message "Infinity" mais on considère pour cet exemple que ce n'est pas le cas.
public class Diviser {
	
	public String division (Double numberOne, Double numberTwo) {
		try {
			Double result = numberOne/numberTwo;
			return result.toString();
		} catch (Exception e) {
			String erreur = "Division par zéro !";
			return erreur;
			
		}
		
		
	}

}

// 5) Mise en forme 


// Mise en forme horizontale : le code ne permet pas de distinguer visuellement les opérations correspondant à un bloc.
// Or un bloc correspond à une fonctionnalité : vérification à l'intérieur d'une condition, itération à l'intérieur d'une boucle etc...

private Cell  test(XSSFSheet sheet,  int rowIndex, int cellIndex, String cellContent ) {
Row row;
if (sheet.getRow(rowIndex)!=null) {
row = sheet.getRow(rowIndex);
} else {
row = sheet.createRow(rowIndex);
}
Cell cell = row.createCell(cellIndex);
cell.setCellValue(cellContent);
return cell;
}

// Après indentation , on voit directement quelles opérations appartiennent au même bloc.

private Cell  test(XSSFSheet sheet,  int rowIndex, int cellIndex, String cellContent ) {
	Row row;
	if (sheet.getRow(rowIndex)!=null) {
		row = sheet.getRow(rowIndex);
	} else {
		row = sheet.createRow(rowIndex);
	}
	Cell cell = row.createCell(cellIndex);
	cell.setCellValue(cellContent);
	return cell;
}