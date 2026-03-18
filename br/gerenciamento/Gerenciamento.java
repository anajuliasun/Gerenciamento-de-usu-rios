/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
/*
Comando criar container:
docker run -d --name mysql-app -p 3307:3306 -e MYSQL_ROOT_PASSWORD=minhasenhasecreta -v mysql_data:/var/lib/mysql mysql:8
Rodar container:
docker start mysql-app
*/

package br.gerenciamento;
import ui.MenuPrincipal;

/**
 *
 * @author Administrator
 */
public class Gerenciamento {

    public static void main(String[] args) {
        new MenuPrincipal();
    }
}
