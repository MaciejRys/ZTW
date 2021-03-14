<?php
/**
 * The base configuration for WordPress
 *
 * The wp-config.php creation script uses this file during the
 * installation. You don't have to use the web site, you can
 * copy this file to "wp-config.php" and fill in the values.
 *
 * This file contains the following configurations:
 *
 * * MySQL settings
 * * Secret keys
 * * Database table prefix
 * * ABSPATH
 *
 * @link https://wordpress.org/support/article/editing-wp-config-php/
 *
 * @package WordPress
 */

// ** MySQL settings - You can get this info from your web host ** //
/** The name of the database for WordPress */
define( 'DB_NAME', 'wordpress' );

/** MySQL database username */
define( 'DB_USER', 'root' );

/** MySQL database password */
define( 'DB_PASSWORD', '' );

/** MySQL hostname */
define( 'DB_HOST', 'localhost' );

/** Database Charset to use in creating database tables. */
define( 'DB_CHARSET', 'utf8mb4' );

/** The Database Collate type. Don't change this if in doubt. */
define( 'DB_COLLATE', '' );

/**#@+
 * Authentication Unique Keys and Salts.
 *
 * Change these to different unique phrases!
 * You can generate these using the {@link https://api.wordpress.org/secret-key/1.1/salt/ WordPress.org secret-key service}
 * You can change these at any point in time to invalidate all existing cookies. This will force all users to have to log in again.
 *
 * @since 2.6.0
 */
define( 'AUTH_KEY',         'L-d ( #GLpP&;sij;8%w|pjaG$QqAh9[bU[QMZw~e^~,gd#n|2*@;obe7^%?<c<(' );
define( 'SECURE_AUTH_KEY',  'TfYtM$25L2DU]d@SdpLxHR_wMSe%xo$lEuL|SjXd1~kX8K :(RoG-<m{;QX>Gl>,' );
define( 'LOGGED_IN_KEY',    'GZ**WA|%0zr)ylD|81~=_<j/)TH|NA,Fy_@m>]qg. uwQ*O$hZ[#j&78>k7kP4H5' );
define( 'NONCE_KEY',        'Av&jeGGu=IYAV&B}`:xyuu^J?w5srWyW[a}*+*8Af[r,$wLcW]A`Z/qihM[cnsQy' );
define( 'AUTH_SALT',        'iZSEqRV.e.cVJUzVE{!!]ME6): |3(O|;?V$3qZi}-s, w)=rJXRgzcH@J|~+$l`' );
define( 'SECURE_AUTH_SALT', 'odKh&Pi?QU6~pGijKO%5*`%ioPC@)2bG:g=dcxU==1;4G y[TN&mB?wPPYlH,Xhi' );
define( 'LOGGED_IN_SALT',   'Gi`}3p?+4flXl;2{K%%:~kT{[z(73XZnc ck%oaueTPHUWweB@BmO=Gy[w-BU&Pf' );
define( 'NONCE_SALT',       '+PT6<9F_yZWOO,DuF8J)#m^Xel* ^kIFY.ZsAKw0*JVIPc359RY-NBN-WbPGsj<@' );

/**#@-*/

/**
 * WordPress Database Table prefix.
 *
 * You can have multiple installations in one database if you give each
 * a unique prefix. Only numbers, letters, and underscores please!
 */
$table_prefix = 'wp_';

/**
 * For developers: WordPress debugging mode.
 *
 * Change this to true to enable the display of notices during development.
 * It is strongly recommended that plugin and theme developers use WP_DEBUG
 * in their development environments.
 *
 * For information on other constants that can be used for debugging,
 * visit the documentation.
 *
 * @link https://wordpress.org/support/article/debugging-in-wordpress/
 */
define( 'WP_DEBUG', false );

/* That's all, stop editing! Happy publishing. */

/** Absolute path to the WordPress directory. */
if ( ! defined( 'ABSPATH' ) ) {
	define( 'ABSPATH', __DIR__ . '/' );
}

/** Sets up WordPress vars and included files. */
require_once ABSPATH . 'wp-settings.php';
