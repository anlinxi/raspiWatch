package com.faker.raspi.raspiwatch.util;

import cn.hutool.core.util.ReUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TopTest extends TestBase {

    public static void main(String[] args) {
        //正则查找匹配的第一个字符串
        String cpuUse = ReUtil.get("(?<=%Cpu(s):).*(?<=us,)", top, 0);
        logger.info(cpuUse);
        Pattern pattern = Pattern.compile("(.*),(.*)");
        Matcher matcher = pattern.matcher(top);
        if (matcher.find()) {
            logger.info(matcher.group());
        } else {
            logger.warn("没找到...");
        }
        //md正则太难了，还不如截取呢...
        String cpuStart = "%Cpu(s): ";
        String cpu = top.substring(top.indexOf(cpuStart) + cpuStart.length(), top.indexOf(" us,"));
        logger.info(cpu);
    }

    private static String top = "top - 20:55:19 up 5 days,  3:55,  3 users,  load average: 1.98, 1.01, 0.98\n" +
            "Tasks: 222 total,   2 running, 220 sleeping,   0 stopped,   0 zombie\n" +
            "%Cpu(s): 78.4 us, 15.9 sy,  0.0 ni,  5.7 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st\n" +
            "MiB Mem :   3795.3 total,    242.7 free,   2869.4 used,    683.3 buff/cache\n" +
            "MiB Swap:    100.0 total,      0.0 free,    100.0 used.    616.0 avail Mem \n" +
            "\n" +
            "  PID USER      PR  NI    VIRT    RES    SHR S  %CPU  %MEM     TIME+ COMMAND\n" +
            "10755 pi        20   0 5146512   1.1g  34232 S 100.0  30.7  17:04.49 java\n" +
            "14283 pi        20   0 2818200  47456  21252 S  85.0   1.2   0:03.11 java\n" +
            "  971 root      20   0  251100  69544  37376 R  60.0   1.8  55:09.45 Xorg\n" +
            "14293 pi        20   0 3475136  41228  15780 S  45.0   1.1   0:03.57 java\n" +
            "  361 root      -2   0       0      0      0 S  10.0   0.0   5:11.37 v3d_bin\n" +
            " 1255 root      20   0  634240  38316   7156 S  10.0   1.0  28:30.61 BT-Task\n" +
            "14325 pi        20   0    9284   3276   2624 R  10.0   0.1   0:00.04 top\n" +
            "  362 root      -2   0       0      0      0 S   5.0   0.0   5:36.34 v3d_render\n" +
            " 2290 pi        20   0 3964696 476484      0 S   5.0  12.3 122:32.09 java\n" +
            "    1 root      20   0  165612   5980   3120 S   0.0   0.2   0:38.63 systemd\n" +
            "    2 root      20   0       0      0      0 S   0.0   0.0   0:01.19 kthreadd\n" +
            "    3 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 rcu_gp\n" +
            "    4 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 rcu_par_gp\n" +
            "    8 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 mm_percpu+\n" +
            "    9 root      20   0       0      0      0 S   0.0   0.0   0:00.00 rcu_tasks+\n" +
            "   10 root      20   0       0      0      0 S   0.0   0.0   0:00.00 rcu_tasks+\n" +
            "   11 root      20   0       0      0      0 S   0.0   0.0   0:00.00 rcu_tasks+\n" +
            "   12 root      20   0       0      0      0 S   0.0   0.0   0:26.63 ksoftirqd+\n" +
            "   13 root      20   0       0      0      0 I   0.0   0.0  10:50.08 rcu_preem+\n" +
            "   14 root      rt   0       0      0      0 S   0.0   0.0   0:00.10 migration+\n" +
            "   15 root      20   0       0      0      0 S   0.0   0.0   0:00.00 cpuhp/0\n" +
            "   16 root      20   0       0      0      0 S   0.0   0.0   0:00.00 cpuhp/1\n" +
            "   17 root      rt   0       0      0      0 S   0.0   0.0   0:00.10 migration+\n" +
            "   18 root      20   0       0      0      0 S   0.0   0.0   0:09.11 ksoftirqd+\n" +
            "   21 root      20   0       0      0      0 S   0.0   0.0   0:00.00 cpuhp/2\n" +
            "   22 root      rt   0       0      0      0 S   0.0   0.0   0:00.11 migration+\n" +
            "   23 root      20   0       0      0      0 S   0.0   0.0   0:09.93 ksoftirqd+\n" +
            "   26 root      20   0       0      0      0 S   0.0   0.0   0:00.00 cpuhp/3\n" +
            "   27 root      rt   0       0      0      0 S   0.0   0.0   0:00.11 migration+\n" +
            "   28 root      20   0       0      0      0 S   0.0   0.0   0:08.80 ksoftirqd+\n" +
            "   31 root      20   0       0      0      0 S   0.0   0.0   0:00.01 kdevtmpfs\n" +
            "   32 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 netns\n" +
            "   33 root      20   0       0      0      0 S   0.0   0.0   0:00.00 kauditd\n" +
            "   35 root      20   0       0      0      0 S   0.0   0.0   0:00.77 khungtaskd\n" +
            "   36 root      20   0       0      0      0 S   0.0   0.0   0:00.21 oom_reaper\n" +
            "   37 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 writeback\n" +
            "   38 root      20   0       0      0      0 S   0.0   0.0   0:28.81 kcompactd0\n" +
            "   58 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 kblockd\n" +
            "   59 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 blkcg_pun+\n" +
            "   60 root     -51   0       0      0      0 S   0.0   0.0   0:00.00 watchdogd\n" +
            "   63 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 rpciod\n" +
            "   65 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 xprtiod\n" +
            "   66 root      20   0       0      0      0 S   0.0   0.0   1:04.36 kswapd0\n" +
            "   67 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 nfsiod\n" +
            "   68 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 kthrotld\n" +
            "   69 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 iscsi_eh\n" +
            "   70 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 iscsi_des+\n" +
            "   73 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 DWC Notif+\n" +
            "   74 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 uas\n" +
            "   76 root       1 -19       0      0      0 S   0.0   0.0   0:00.29 vchiq-slo+\n" +
            "   77 root       1 -19       0      0      0 S   0.0   0.0   0:00.10 vchiq-rec+\n" +
            "   78 root       0 -20       0      0      0 S   0.0   0.0   0:00.00 vchiq-syn+\n" +
            "   79 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 zswap-shr+\n" +
            "   81 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 sdhci\n" +
            "   82 root     -51   0       0      0      0 S   0.0   0.0   0:00.00 irq/32-mm+\n" +
            "   83 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 mmc_compl+\n" +
            "   87 root      20   0       0      0      0 S   0.0   0.0   0:38.87 jbd2/mmcb+\n" +
            "   88 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 ext4-rsv-+\n" +
            "   90 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 ipv6_addr+\n" +
            "  135 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 cryptd\n" +
            "  292 root      20   0       0      0      0 S   0.0   0.0   0:00.00 vchiq-kee+\n" +
            "  293 root      10 -10       0      0      0 S   0.0   0.0   0:00.00 SMIO\n" +
            "  302 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 mmal-vchiq\n" +
            "  312 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 mmal-vchiq\n" +
            "  316 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 mmal-vchiq\n" +
            "  318 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 mmal-vchiq\n" +
            "  343 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 cfg80211\n" +
            "  348 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 brcmf_wq/+\n" +
            "  349 root      20   0       0      0      0 S   0.0   0.0   1:25.48 brcmf_wdo+\n" +
            "  363 root      -2   0       0      0      0 S   0.0   0.0   0:03.75 v3d_tfu\n" +
            "  364 root      -2   0       0      0      0 S   0.0   0.0   0:00.00 v3d_csd\n" +
            "  366 root      -2   0       0      0      0 S   0.0   0.0   0:00.00 v3d_cache+\n" +
            "  367 root     -51   0       0      0      0 S   0.0   0.0   0:00.00 card1-crt+\n" +
            "  462 root      20   0    6684   1052    956 S   0.0   0.0   0:01.42 cron\n" +
            "  465 nobody    20   0    4488     32      0 S   0.0   0.0   0:03.76 thd\n" +
            "  477 root      39  19   13012     48      0 S   0.0   0.0   0:00.19 alsactl\n" +
            "  488 message+  20   0    7668   2712   1408 S   0.0   0.1   0:04.25 dbus-daem+\n" +
            "  492 root      20   0   12412    456    384 S   0.0   0.0   0:02.27 wpa_suppl+\n" +
            "  496 root      20   0  219848   2728   1024 S   0.0   0.1   1:50.18 rsyslogd\n" +
            "  497 root      20   0  390816   4012   2120 S   0.0   0.1   0:00.98 udisksd\n" +
            "  498 root      20   0   92268    412    300 S   0.0   0.0   0:07.61 rngd\n" +
            "  505 root      20   0  314852   1144      0 S   0.0   0.0   0:00.40 ModemMana+\n" +
            "  511 root      20   0   16032   3724   2468 S   0.0   0.1   0:01.85 systemd-l+\n" +
            "  541 root      20   0    3000   1432   1244 S   0.0   0.0   0:01.76 dhcpcd\n" +
            "  609 root      20   0    9608    168      0 S   0.0   0.0   0:00.02 pure-ftpd\n" +
            "  650 root      20   0  237308   4204   2116 S   0.0   0.1   0:00.91 polkitd\n" +
            "  665 root      20   0 1426376  30632   2868 S   0.0   0.8  21:15.73 dockerd\n" +
            "  666 root      20   0   33380   3756   1952 S   0.0   0.1   0:18.29 nmbd\n" +
            "  684 aria2     20   0   58204   5540   2916 S   0.0   0.1   1:51.59 aria2c\n" +
            "  685 redis     20   0   51452   2016   1492 S   0.0   0.1  49:30.64 redis-ser+\n" +
            "  688 root      20   0    7628      0      0 S   0.0   0.0   0:00.01 vncserver+\n" +
            "  691 root      20   0  106672   6832      0 S   0.0   0.2   0:00.66 unattende+\n" +
            "  710 colord    20   0  244124   2864      0 S   0.0   0.1   0:00.94 colord\n" +
            "  729 root      20   0   12924   1976   1592 S   0.0   0.1   0:45.51 wpa_suppl+\n" +
            "  739 root      20   0  235308   1460    888 S   0.0   0.0   0:00.20 lightdm\n" +
            "  744 root      20   0  117912  24444   9960 S   0.0   0.6   3:15.72 vncserver+\n" +
            "  745 root      20   0   12100   1616   1440 S   0.0   0.0   0:55.16 sshd\n" +
            "  766 root      20   0  102840    456    164 S   0.0   0.0   0:00.01 nginx\n" +
            "  777 www       20   0  123652   6528      0 S   0.0   0.2   0:00.08 nginx\n" +
            "  778 www       20   0  123652   6016      0 S   0.0   0.2   0:00.09 nginx\n" +
            "  779 www       20   0  124048   4604     56 S   0.0   0.1   0:00.15 nginx\n" +
            "  780 www       20   0  124516   3064     60 S   0.0   0.1   0:01.94 nginx\n" +
            "  781 www       20   0  103020   1232    864 S   0.0   0.0   0:03.75 nginx\n" +
            "  941 root      20   0    7072    496      4 S   0.0   0.0   0:00.15 mysqld_sa+\n" +
            "  972 root      20   0    5756    496      0 S   0.0   0.0   0:00.02 login\n" +
            " 1172 root      20   0   94200    560    204 S   0.0   0.0   0:24.94 php-fpm\n" +
            " 1180 root      20   0   67892    388     12 S   0.0   0.0   0:25.69 php-fpm\n" +
            " 1182 www       20   0   72208   7080      8 S   0.0   0.2   0:01.40 php-fpm\n" +
            " 1183 www       20   0   94200    384      4 S   0.0   0.0   0:00.00 php-fpm\n" +
            " 1185 www       20   0   94200    384      8 S   0.0   0.0   0:00.00 php-fpm\n" +
            " 1186 www       20   0   82448  19212   2152 S   0.0   0.5   6:55.33 php-fpm\n" +
            " 1187 www       20   0   94200    384      8 S   0.0   0.0   0:00.00 php-fpm\n" +
            " 1188 www       20   0   94200    388      8 S   0.0   0.0   0:00.00 php-fpm\n" +
            " 1189 www       20   0   94200    388      8 S   0.0   0.0   0:00.00 php-fpm\n" +
            " 1190 www       20   0   70160   5132     12 S   0.0   0.1   0:01.04 php-fpm\n" +
            " 1191 www       20   0   74256  10200     12 S   0.0   0.3   0:01.43 php-fpm\n" +
            " 1200 www       20   0   74256   8880     12 S   0.0   0.2   0:01.29 php-fpm\n" +
            " 1239 mysql     20   0 2726848  71968   1068 S   0.0   1.9   8:25.81 mysqld\n" +
            " 1271 root      20   0    2244      4      0 S   0.0   0.0   0:00.00 hciattach\n" +
            " 1272 root       0 -20       0      0      0 I   0.0   0.0   0:00.99 kworker/u+\n" +
            " 1286 root      20   0   11652   1156      0 S   0.0   0.0   0:05.71 bluetoothd\n" +
            " 1287 root      20   0 1386284  12932   6076 S   0.0   0.3  19:56.27 docker-co+\n" +
            " 1383 pi        20   0   16928   1580      4 S   0.0   0.0   0:00.49 systemd\n" +
            " 1385 pi        20   0   19664    508      0 S   0.0   0.0   0:00.00 (sd-pam)\n" +
            " 1426 pi        20   0    7996    764      0 S   0.0   0.0   0:00.10 bash\n" +
            " 1510 root      20   0  163316   1016      0 S   0.0   0.0   0:00.11 lightdm\n" +
            " 1524 pi        20   0  251980   3544   1712 S   0.0   0.1   0:12.92 lxsession\n" +
            " 1526 root      20   0  286624  50404   9020 S   0.0   1.3   0:55.06 BT-Panel\n" +
            " 1538 pi        20   0    6872   1988   1292 S   0.0   0.1   0:00.49 dbus-daem+\n" +
            " 1628 root       0 -20       0      0      0 I   0.0   0.0   0:23.09 kworker/2+\n" +
            " 1631 root      20   0   52392   5468   2836 S   0.0   0.1   0:01.91 smbd\n" +
            " 1633 pi        20   0    5344    100      0 S   0.0   0.0   0:02.12 ssh-agent\n" +
            " 1664 pi        20   0 1053728  52612  23200 S   0.0   1.4   0:05.37 fcitx\n" +
            " 1680 root      20   0   49096   3048    624 S   0.0   0.1   0:00.64 smbd-noti+\n" +
            " 1681 root      20   0   49104   3048    628 S   0.0   0.1   0:00.64 cleanupd\n" +
            " 1687 pi        20   0    6660   1672   1292 S   0.0   0.0   0:00.24 dbus-daem+\n" +
            " 1693 pi        39  19    4848    116      0 S   0.0   0.0   0:00.00 fcitx-dbu+\n" +
            " 1694 pi        20   0  237080   1688    696 S   0.0   0.0   0:00.19 gvfsd\n" +
            " 1695 root      20   0   52660   7728   4944 S   0.0   0.2   0:05.47 lpqd\n" +
            " 1701 pi        20   0  381036    976      4 S   0.0   0.0   0:00.04 gvfsd-fuse\n" +
            " 1706 pi        20   0   85700  11200   4164 S   0.0   0.3   0:06.03 openbox\n" +
            " 1707 pi        20   0  177636   4796   3328 S   0.0   0.1   0:00.08 lxpolkit\n" +
            " 1715 pi        20   0  989508  27132   9476 S   0.0   0.7   5:24.96 lxpanel\n" +
            " 1717 pi        20   0  533624  49016  10204 S   0.0   1.3   0:15.00 pcmanfm\n" +
            " 1724 pi        20   0    5344     96      0 S   0.0   0.0   0:00.00 ssh-agent\n" +
            " 1731 pi        20   0   55616  23816   3320 S   0.0   0.6   0:01.65 applet.py\n" +
            " 1739 pi        20   0  233588    468      0 S   0.0   0.0   0:00.00 agent\n" +
            " 1753 pi        20   0    5404    216      0 S   0.0   0.0   0:00.01 xcompmgr\n" +
            " 1757 pi         9 -11  497464   3700   1796 S   0.0   0.1   0:02.99 pulseaudio\n" +
            " 1766 rtkit     21   1  152440    276      0 S   0.0   0.0   0:05.79 rtkit-dae+\n" +
            " 1770 pi        20   0  159632    608      0 S   0.0   0.0   0:00.06 gvfsd-met+\n" +
            " 1828 pi        20   0  159308    736      4 S   0.0   0.0   0:00.04 menu-cach+\n" +
            " 1830 root      10 -10       0      0      0 S   0.0   0.0   0:00.00 krfcommd\n" +
            " 1834 pi        20   0  275252   2104      0 S   0.0   0.1   0:00.15 gvfs-udis+\n" +
            " 1838 pi        20   0  233104    700      0 S   0.0   0.0   0:00.02 gvfs-mtp-+\n" +
            " 1842 pi        20   0  235028    828      0 S   0.0   0.0   0:00.02 gvfs-gpho+\n" +
            " 1846 pi        20   0  233272    652      0 S   0.0   0.0   0:00.02 gvfs-goa-+\n" +
            " 1851 pi        20   0  314264   1108      0 S   0.0   0.0   0:00.03 gvfs-afc-+\n" +
            " 1914 pi        20   0  314328   1384      0 S   0.0   0.0   0:00.12 gvfsd-tra+\n" +
            " 2169 root      20   0   10204   4472   3448 S   0.0   0.1   0:36.24 openvpn\n" +
            " 2172 root      20   0  803936   7524   3084 S   0.0   0.2  17:42.74 frpc\n" +
            " 2252 pi        20   0 4323244 475188   3592 S   0.0  12.2  16:51.55 java\n" +
            " 2394 root      20   0   17400   1628   1304 S   0.0   0.0   0:22.16 sendmail-+\n" +
            " 3079 root      20   0  249880   4144   2764 S   0.0   0.1   0:03.16 upowerd\n" +
            " 3250 root      20   0   16396  10124   9356 S   0.0   0.3   0:09.18 vncagent\n" +
            " 3263 root       0 -20       0      0      0 I   0.0   0.0   0:01.68 kworker/1+\n" +
            " 3266 pi        20   0   46452  18500  13804 S   0.0   0.5   0:00.60 vncserver+\n" +
            " 3274 pi        20   0   33932  14412  12040 S   0.0   0.4   0:00.19 vncserver+\n" +
            " 3979 root      20   0       0      0      0 I   0.0   0.0   0:01.08 kworker/3+\n" +
            " 4488 www       20   0   74412   9040     12 S   0.0   0.2   0:00.97 php-fpm\n" +
            " 4492 www       20   0   72184   6940     16 S   0.0   0.2   0:00.91 php-fpm\n" +
            " 4493 www       20   0   72212   6920     16 S   0.0   0.2   0:00.46 php-fpm\n" +
            " 4809 root       0 -20       0      0      0 I   0.0   0.0   0:00.92 kworker/u+\n" +
            " 4883 root       5 -15       0      0      0 S   0.0   0.0   0:00.33 khidpd_05+\n" +
            " 5312 root      20   0   28796   4756   2596 S   0.0   0.1   0:00.36 cupsd\n" +
            " 5313 root      20   0  171780   2356    904 S   0.0   0.1   0:00.12 cups-brow+\n" +
            " 8748 pi        20   0  314404   1008      0 S   0.0   0.0   0:00.09 gvfsd-net+\n" +
            " 8759 pi        20   0  155792    584      4 S   0.0   0.0   0:00.02 dconf-ser+\n" +
            " 8776 pi        20   0  312644   2208   1116 S   0.0   0.1   0:00.28 gvfsd-dns+\n" +
            " 8942 root      20   0       0      0      0 I   0.0   0.0   0:07.20 kworker/u+\n" +
            " 9404 root      20   0       0      0      0 I   0.0   0.0   0:07.99 kworker/u+\n" +
            "10705 pi        20   0  292616  30300  21756 S   0.0   0.8   0:00.72 x-termina+\n" +
            "10712 pi        20   0    6668   2828   2596 S   0.0   0.1   0:00.00 sh\n" +
            "10713 pi        20   0    6800   3064   2664 S   0.0   0.1   0:00.02 idea.sh\n" +
            "11209 pi        20   0 4363716 102728  16624 S   0.0   2.6   0:23.67 java\n" +
            "11379 root      20   0       0      0      0 I   0.0   0.0   0:01.49 kworker/u+\n" +
            "11761 root      20   0       0      0      0 I   0.0   0.0   0:01.34 kworker/0+\n" +
            "11899 pi        20   0 4013476  53936  16260 S   0.0   1.4   0:07.41 java\n" +
            "12465 root      20   0       0      0      0 I   0.0   0.0   0:02.49 kworker/0+\n" +
            "12507 root      20   0       0      0      0 I   0.0   0.0   0:00.27 kworker/2+\n" +
            "12739 root      20   0       0      0      0 I   0.0   0.0   0:00.12 kworker/1+\n" +
            "13000 pi        20   0  200960  14268   7892 S   0.0   0.4  84:27.55 lxtask\n" +
            "13113 root      20   0       0      0      0 I   0.0   0.0   0:00.13 kworker/2+\n" +
            "13234 root      20   0       0      0      0 I   0.0   0.0   0:00.10 kworker/1+\n" +
            "13448 root      20   0       0      0      0 I   0.0   0.0   0:00.82 kworker/0+\n" +
            "13474 root      20   0       0      0      0 I   0.0   0.0   0:00.00 kworker/3+\n" +
            "13477 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 kworker/1+\n" +
            "13575 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 kworker/0+\n" +
            "13633 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 kworker/3+\n" +
            "13649 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 kworker/2+\n" +
            "13810 root      20   0   13684   7148   6072 S   0.0   0.2   0:00.07 sshd\n" +
            "13820 pi        20   0   13684   4496   3420 S   0.0   0.1   0:00.01 sshd\n" +
            "13821 pi        20   0    8128   4780   3176 S   0.0   0.1   0:00.12 bash\n" +
            "13860 root      20   0       0      0      0 I   0.0   0.0   0:00.01 kworker/1+\n" +
            "13948 root      20   0       0      0      0 I   0.0   0.0   0:00.71 kworker/u+\n" +
            "13981 root      20   0       0      0      0 I   0.0   0.0   0:00.07 kworker/0+\n" +
            "14062 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 kworker/0+\n" +
            "14124 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 kworker/1+\n" +
            "14178 root      20   0       0      0      0 I   0.0   0.0   0:00.00 kworker/2+\n" +
            "14232 root       0 -20       0      0      0 I   0.0   0.0   0:00.00 kworker/2+\n" +
            "14305 root      20   0   12100   5992   5176 S   0.0   0.2   0:00.05 sshd\n" +
            "14308 sshd      20   0   12100   4996   4144 S   0.0   0.1   0:00.03 sshd\n" +
            "14322 root      20   0   12100   6204   5388 S   0.0   0.2   0:00.09 sshd\n" +
            "14323 sshd      20   0   12100   4848   3992 S   0.0   0.1   0:00.03 sshd\n" +
            "17507 root       0 -20       0      0      0 I   0.0   0.0   0:05.96 kworker/3+\n" +
            "20093 root       0 -20       0      0      0 I   0.0   0.0   0:20.96 kworker/0+\n" +
            "20445 root      20   0   19664   1916    704 S   0.0   0.0   0:00.51 systemd-u+\n" +
            "21880 avahi     20   0    6128   1964   1368 S   0.0   0.1   0:40.40 avahi-dae+\n" +
            "21881 avahi     20   0    5872    292      0 S   0.0   0.0   0:00.00 avahi-dae+\n" +
            "23197 systemd+  20   0   88792   2356   1428 S   0.0   0.1   0:00.72 systemd-t+\n" +
            "23200 root      20   0   85576  42860  36428 S   0.0   1.1   3:18.66 systemd-j+\n" +
            "32183 lp        20   0   15188    812      0 S   0.0   0.0   0:00.01 dbus\n" +
            "\n" +
            "20:55:19.401 [main] INFO com.faker.raspi.raspiwatch.util.SystemInfoReader - 树莓派CPU使用信息:null%\n" +
            "\n" +
            "Process finished with exit code 0\n";
}
