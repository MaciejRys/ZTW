<?php
/**
 * Plugin Name: ZTWPlugin
 * Plugin URI:  http://localhost/plugins/ZTWPlugin
 * Description: opis
 * Version:     1.0
 */




function ZTWP_admin_action_register_menu(){
    add_options_page("ZTWPlugin","ZTWPlugin settings","manage_options","ZTWP","ZTWP_admin_page");
}

add_action("admin_menu", "ZTWP_admin_action_register_menu");
function ZTWP_admin_page(){

//    global $_POST;
//
//    if (isset($_POST['naph_do_change'])){
//        if($_POST[])
//    }
    ?>
    <h1>ZTWP Highliter</h1>
    <?php
}