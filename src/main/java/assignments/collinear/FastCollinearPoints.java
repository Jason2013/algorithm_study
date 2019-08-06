import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
/**
 * @author evasean www.cnblogs.com/evasean/
 */
public class FastCollinearPoints {
    
    private Point[] points; //鎻愪氦浣滀笟鏃舵彁绀鸿緭鍏ョ殑缁欐瀯閫犲嚱鏁扮殑鏁扮粍鍐呭涓嶈兘鍙戠敓鏀瑰彉锛屾晠绫讳腑鍔犱釜鏁扮粍灏嗚緭鍏ュ弬鏁板瓨璧锋潵
    private final LineSegment[] segments;
    private int segNum;
    
    private List<PointPair> pointPairList; //瀛樺偍鏋勬垚LineSegment鐨勮捣鐐瑰拰缁堢偣Point瀵�
    /**
     * LineSegment绫讳笉鍏佽鍙樺姩锛屼絾鏄彲浣跨敤鐏垫椿搴﹀彈闄愶紝鑷繁鏂板姞涓唴閮ㄧ被浣跨敤
     * 鏈被鐢ㄦ潵瀛樺偍鍙瀯鎴怢ineSegment鐨勮捣鐐瑰拰缁堢偣point瀵�
     * 鐢变簬鍦ㄩ亶鍘嗚繃绋嬩腑浼氬瓨鍦ㄥ寘鍚叧绯荤殑璧风偣鍜岀粓鐐筽oint瀵癸紝浠呬粎闈燣ineSegment绫昏瘑鍒寘鍚叧绯荤殑鏁堢巼浼氬緢浣�
     * 姝ょ被涓姞浜唖lope鏉ヨ褰曞氨鍙互寰堝ぇ鐨勬彁楂樻晥鐜囦簡锛屽洜涓轰竴涓偣鍜屼竴涓枩鐜囧氨纭畾浜嗕竴鏉＄洿绾�
     * 涓嶉渶瑕佸啀杩涜棰濆姣旇緝鍜岃绠�
     * 鍥犱负鐢变簬PointPair鏄points浠庡墠鍒板悗閬嶅巻浜х敓鐨勶紝鎵�浠ュ鏋滀袱涓狿ointPair瀛樺湪鍖呭惈鍏崇郴锛岄偅涔�
     * 杩欎袱涓狿ointPair涓璴argePoint鍜宻lope涓�瀹氱浉绛�
     * 浣唖mallPoint涓嶇浉绛夛紝smallPoint鏇村皬鐨勯偅涓狿ointPair鍖呭惈浜嗗彟涓�涓狿ointPair
     * 杩欐槸LineSegment鍘婚噸鐨勫叧閿�
     * @author evasean www.cnblogs.com/evasean/
     */
    private class PointPair{
        private final Point smallPoint;
        private final Point largePoint;
        private final double slope; 
        public PointPair(Point smallPoint, Point largePoint){
            this.smallPoint = smallPoint;
            this.largePoint = largePoint;
            this.slope = largePoint.slopeTo(smallPoint);
        }
        public Point getLargePoint(){
            return this.largePoint;
        }
        public Point getSmallPoint(){
            return this.smallPoint;
        }
        public double getSlope(){
            return this.slope;
        }
        public int compareTo(PointPair that) {
            Point l1 = this.getLargePoint();
            Point l2 = that.getLargePoint();
            double s1 = this.getSlope();
            double s2 = that.getSlope();
            if(l1.compareTo(l2) > 0) return 1;
            else if(l1.compareTo(l2) < 0) return -1;
            else{
                if(s1>s2) return 1;
                else if(s1<s2) return -1;
                else return 0;
            }
        }
        /**
         * 鍒ゆ柇PointPair涓殑鍖呭惈鍏崇郴鏃堕渶瑕佺敤鍒版瘮杈冨櫒
         * 姝ゆ瘮杈冨櫒鏄互largePoint涓烘瘮杈冪殑涓昏鍏冪礌锛宻lope涓烘瑕佸厓绱�
         * smallPoint涓嶅弬姣旇緝澶у皬鐨勮�冩牳锛屼粎浠呭湪涓や釜PointPair鐩哥瓑鏃剁敤浣滃垽鏂寘鍚叧绯讳箣鐢�
         * 涓や釜PointPair pp1 鍜� pp2涓�
         * if pp1.largePoint > pp2.largePoint --> pp1 > pp2
         * else if pp1.largePoint < pp2.largePoint --> pp1 < pp2
         * if pp1.largePoint == pp2.largePoint && pp1.slope > pp2.slope --> pp1 > pp2
         * if pp1.largePoint == pp2.largePoint && pp1.slope < pp2.slope --> pp1 < pp2
         * if pp1.largePoint == pp2.largePoint && pp1.slope == pp2.slope --> pp1 == pp2
         * @return
         */
        public Comparator<PointPair> pointPairComparator() {
            return new PointPairComparator();
        }
        private class PointPairComparator implements Comparator<PointPair>{
            @Override
            public int compare(PointPair pp1, PointPair pp2) {
                // TODO Auto-generated method stub
                Point l1 = pp1.getLargePoint();
                Point l2 = pp2.getLargePoint();
                double s1 = pp1.getSlope();
                double s2 = pp2.getSlope();
                if(l1.compareTo(l2) > 0) return 1;
                else if(l1.compareTo(l2) < 0) return -1;
                else{
                    return Double.compare(s1, s2); //double鍏冪礌鐢―ouble.compare杩涜姣旇緝鏇寸簿纭�
                }
            }
        }
    }
    
    public FastCollinearPoints(Point[] inpoints) {
        // finds all line segments containing 4 or more points
        if (inpoints == null)
            throw new IllegalArgumentException("Constructor argument Point[] is null!");
        // finds all line segments containing 4 points
        for (int i=0;i<inpoints.length;i++) {
            if (inpoints[i] == null)
                throw new IllegalArgumentException("there is null in constructor argument");
        }
        points = new Point[inpoints.length];
        for (int i=0;i<inpoints.length;i++) {
            points[i] = inpoints[i];
        }
        Arrays.sort(points); //瀵规湰瀵硅薄鐨勭鏈夋暟缁勮繘琛屾帓搴�
        for (int i=0;i<points.length-1;i++) {
            if (points[i].compareTo(points[i+1]) == 0) // 涓庡墠涓�涓厓绱犵浉绛�
                throw new IllegalArgumentException("there exists repeated points!");
        }
        //浣滀笟鎻愪氦鏃舵彁绀洪殢鏈虹┛鎻掗『搴忚皟鐢╪umberOfSegments()鍜宻egment()鏂规硶杩斿洖缁撴灉瑕佹眰绋冲畾
        //閭ｄ箞鏋勯�犲嚱鏁颁腑灏辫鎶奓ineSegment鎵惧ソ
        findPointPairForLineSegment(points);
        segments = generateLineSegment();
    }

    /**
     * 瀵绘壘婊¤冻LineSegment鐨凱ointPair
     * @param points
     */
    private void findPointPairForLineSegment(Point[] points){
        int pNum = points.length;
        pointPairList = new ArrayList<PointPair>();
        for (int i = 0; i < pNum - 3; i++) { //i涓嶉渶瑕侀亶鍘嗘渶鍚庝笁涓妭鐐癸紝鍥犱负鑷冲皯鍥涗釜鑺傜偣鎵嶈兘缁勬垚LineSegment
            if(points[i]==null)
                throw new IllegalArgumentException("there is null in constructor argument");
            Point origin = points[i]; //i澶勮妭鐐逛綔涓虹浉瀵瑰師鐐�
            Point[] tPoints = new Point[pNum-i-1]; //闇�瑕佺敤鍒伴澶栫┖闂存潵瀛樺偍鏈疆i涔嬪悗鐨勮妭鐐规牴鎹畠浠悇鑷笌鑺傜偣i鐨勭浉瀵规枩鐜囨潵鎺掑簭鐨勭粨鏋�
            int tpNum = 0;
            for (int j = i + 1; j < pNum; j++) {
                tPoints[tpNum++] = points[j];
            }
            //origin.slopeOrder()杩欎釜姣旇緝鍣ㄥ氨鏄憡璇堿rrays.sort寰呮帓搴忕殑閭ｄ簺鑺傜偣tPoints鎺掑簭鐨勪緷鎹槸鍚勮嚜涓庤妭鐐筰鐨勬枩鐜�
            Arrays.sort(tPoints,origin.slopeOrder()); 
            
            int startPostion = 0; //startPosition鐢ㄦ潵璁板綍slope鐩稿悓鐨刾oint浣嶇疆鍖洪棿鐨勮捣濮嬩綅缃�
            double slope = origin.slopeTo(tPoints[0]);
            Map<Integer,Integer> intervalMap = new HashMap<Integer,Integer>(); //璁板綍slope鐩稿悓鐨刾oint浣嶇疆鍖洪棿
            int curPostion = 1;
            for(; curPostion<tpNum; curPostion++){
                if(Double.compare(origin.slopeTo(tPoints[curPostion]), slope)==0)
                    continue;
                else{ //閬嶅巻鑷硈lope涓嶄笌涔嬪墠鐩稿悓鐨勪綅缃�
                    if(curPostion-startPostion >= 3) { //濡傛灉澶т簬3锛屽氨琛ㄧず婊¤冻浜嗙粍鎴怢ineSegment鐨勬潯浠讹紝璁板綍point浣嶇疆鍖洪棿
                        intervalMap.put(startPostion, curPostion-1);//curPostion-1灏辨槸鍖洪棿缁堟鑺傜偣浣嶇疆
                    }
                    slope = origin.slopeTo(tPoints[curPostion]);
                    startPostion = curPostion; //閲嶇疆璧峰鑺傜偣
                }
            }
            if(curPostion-startPostion >= 3) { //tPoints鏈�鍚庝竴涓妭鐐逛篃鍙兘涓庡墠涓�鑺傜偣鏈夌浉鍚岀殑slope
                intervalMap.put(startPostion, curPostion-1);
            }
            //鏍规嵁婊¤冻鏉′欢鐨勫尯闂翠綅缃紝鍒涘缓PointPair
            for(int key : intervalMap.keySet()){
                int value = intervalMap.get(key);
                Point[] linearPoints = new Point[value-key+2];
                linearPoints[0] = origin;
                int l = 1;
                while(key<=value){
                    linearPoints[l++] = tPoints[key++];
                }
                Arrays.sort(linearPoints);
                PointPair pointPair = new PointPair(linearPoints[0], linearPoints[l-1]);
                pointPairList.add(pointPair);
            }
            //娓呯┖涓存椂鏁版嵁锛屼究浜庡瀮鍦惧洖鏀�
            intervalMap.clear();
            intervalMap = null;
            for(int t=0;t<tPoints.length;t++){
                tPoints[t] = null;
            }
            tPoints = null;
        }
    }
    /**
     * 鐢熸垚LineSegment
     * @return
     */
    private LineSegment[]  generateLineSegment(){
        int ppsize = pointPairList.size();
        if(ppsize==0) return new LineSegment[0];;
        PointPair[] pointPairs =  new PointPair[ppsize];
        int i = 0;
        for(PointPair pp : pointPairList){
            pointPairs[i++] = pp;
        }
        pointPairList.clear();
        //鏍规嵁pointPairComparator姣旇緝鍣ㄦ墍瀹氬埗鐨勬帓搴忎緷鎹繘琛屾帓搴忥紝浣垮緱瀛樺湪鍖呭惈鍏崇郴鐨凱ointPair鍙樻垚鐩搁偦鍏崇郴
        Arrays.sort(pointPairs,pointPairs[0].pointPairComparator());
        List<LineSegment> lineSegmentList = new ArrayList<LineSegment>();
        
        PointPair ppls = pointPairs[0]; 
        for(i=1;i<ppsize;i++){
            if(ppls.compareTo(pointPairs[i])==0){ //鐩搁偦鐨凱ointPair鐩哥瓑鏃讹紝鍏锋湁鏇村皬smallPoint鐨凱ointPair鍖洪棿鏇村ぇ
                Point s = pointPairs[i].getSmallPoint();
                if(ppls.getSmallPoint().compareTo(s) > 0)
                    ppls = pointPairs[i];
            }else{
                LineSegment seg = new LineSegment(ppls.getSmallPoint(),ppls.getLargePoint());
                lineSegmentList.add(seg);
                ppls = pointPairs[i];
            }
        }
        LineSegment seg = new LineSegment(ppls.getSmallPoint(),ppls.getLargePoint());
        lineSegmentList.add(seg);
        
        LineSegment[] segments = new LineSegment[lineSegmentList.size()];
        segNum = 0;
        for (LineSegment ls : lineSegmentList) {
            segments[segNum++] = ls;
        }
        return segments;
    }
    
    public int numberOfSegments() {
        // the number of line segments
        return segNum;
    }
    
    public LineSegment[] segments() {
        // the line segments
        //浣滀笟鎻愪氦鏃讹紝鎻愮ず瑕佹眰澶氭璋冪敤segments()鏂规硶杩斿洖鐨勫簲璇ユ槸涓嶅悓鐨勫璞�
        LineSegment[] retseg = new LineSegment[segNum];
        for(int i =0 ;i<segNum;i++){
            retseg[i] = segments[i];
        }
        return retseg;
    }
    
    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
//        In in = new In("collinear/rs1423.txt"); //鏈湴娴嬭瘯浣跨敤
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
//        StdDraw.setPenColor(StdDraw.RED);
//        StdDraw.setPenRadius(0.01);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
