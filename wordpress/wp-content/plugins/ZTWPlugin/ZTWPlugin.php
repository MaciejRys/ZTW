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


function get_random_notice(){
    $notices = get_option('notices');
    return $notices[array_rand($notices,1)];
}

function ZTWP_admin_page(){

    // get _POST variable from globals
    global $_POST;
    // process changes from form
    if(isset($_POST['ZTW_add'])){
        if($_POST['ZTW_add'] == 'Y'){
            if(get_option("notices") != false){
                $new_notice = $_POST['new_notice'];
                $notices = get_option("notices");
                array_push($notices,$new_notice);
                update_option('notices',$notices);
            }else{
                $new_notice = $_POST['new_notice'];
                update_option('notices',array($new_notice));
            }
            echo '<div class="notice notice-success isdismissible"><p>Ogłoszenie dodane. </p></div>';
        }
    }

    if(isset($_POST['ZTW_delete'])){
        if($_POST['ZTW_delete'] == 'Y'){
            if(get_option("notices") != false){
                $notice_to_delete = $_POST['notice_to_delete'];
                $notices = get_option("notices");
                $key = array_search($notice_to_delete, $notices);
                array_splice($notices,$key,1);
                update_option('notices',$notices);
                echo '<div class="notice notice-success isdismissible"><p>Ogłoszenie usunięte. </p></div>';
            }else{
                echo '<div class="notice notice-success isdismissible"><p>Nie ma ogłoszeń do usunięcia. </p></div>';
            }
        }
    }

    //read current option value
    $notices = get_option('notices');
    //display admin page
    ?>
    <div class="wrap">
        <h1>Newly Added Post Highliter</h1>
        <form name="ZTW_form_Add" method="post">
            <input type="hidden" name="ZTW_add" value="Y">
            <p>Dodaj ogłoszenie
            </p>
            <p><input type="text" name="new_notice" value=""></p>
            <p class="submit"><input type="submit" value="Dodaj"></p>
        </form>
        <form name="ZTW_form_Delete" method="post">
            <input type="hidden" name="ZTW_delete" value="Y">
            <p>usuń ogłoszenie
            </p>

            <select name="notice_to_delete"><?php
                var_dump($notices);
                foreach ($notices as $notice) {?>
                <option value ="<?php echo $notice ?>"><?php echo $notice ?>
                </option>
                    <?php
                }
                         ?>
            </select>

            <p><?php echo get_random_notice(); ?><- /*użycie wyświetlenia losowego ogłoszenia*/ - > do usunięcia </p>
            <p class="submit"><input type="submit" value="Usuń"></p>
        </form>



    </div>
    <?php
}

