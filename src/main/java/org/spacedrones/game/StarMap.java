package org.spacedrones.game;

import com.sun.j3d.utils.applet.MainFrame;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import java.applet.Applet;
import java.awt.*;
import java.io.File;
import java.net.URL;

/**
 * Illustrates rendering Java 3D points in a variety of styles.
 */
public class StarMap extends Java3dApplet {
  private static int m_kWidth = 400;

  private static int m_kHeight = 400;

  public StarMap() {
    initJava3d();
  }

  protected double getScale() {
    return 1.0;
  }

  // overridden to use a black background
  // so we can see the points better
  protected Background createBackground() {
    return null;
  }

  protected BranchGroup createSceneBranchGroup() {
    BranchGroup objRoot = super.createSceneBranchGroup();

    TransformGroup objTrans = new TransformGroup();
    objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

    Transform3D yAxis = new Transform3D();
    Alpha rotationAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE, 0, 0,
            14000, 0, 0, 0, 0, 0);

    RotationInterpolator rotator = new RotationInterpolator(rotationAlpha,
            objTrans, yAxis, 0.0f, (float) Math.PI * 2.0f);
    rotator.setSchedulingBounds(getApplicationBounds());
    objTrans.addChild(rotator);

    Switch switchGroup = new Switch();
    switchGroup.setCapability(Switch.ALLOW_SWITCH_WRITE);

    switchGroup.addChild(createPoints(1, 5, false));
    switchGroup.addChild(createPoints(1, 5, true));
    switchGroup.addChild(createPoints(8, 10, false));
    switchGroup.addChild(createPoints(8, 10, true));

    switchGroup.addChild(createPoints(2, 5, false));
    switchGroup.addChild(createPoints(2, 5, true));
    switchGroup.addChild(createPoints(2, 20, false));
    switchGroup.addChild(createPoints(2, 20, true));

    // create a SwitchValueInterpolator to
    // cycle through the child nodes in the Switch Node
    Alpha switchAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE, 0, 0, 15000,
            0, 0, 0, 0, 0);

    SwitchValueInterpolator switchInterpolator = new SwitchValueInterpolator(
            switchAlpha, switchGroup);
    switchInterpolator.setSchedulingBounds(getApplicationBounds());
    switchInterpolator.setEnable(true);

    // WARNING: do not add the SwitchValueInterpolator to the Switch Node!
    objRoot.addChild(switchInterpolator);

    objTrans.addChild(switchGroup);
    objRoot.addChild(objTrans);

    return objRoot;
  }

  private BranchGroup createPoints(final int nPointSize,
                                   final int nNumPoints, boolean bAliased) {
    BranchGroup bg = new BranchGroup();

    String szText = new String();
    szText += (nNumPoints + "X, Size:" + nPointSize + ", aliased: " + bAliased);

    Font3D f3d = new Font3D(new Font("SansSerif", Font.PLAIN, 1),
            new FontExtrusion());
    Text3D label3D = new Text3D(f3d, szText, new Point3f(-5, 0, 0));
    Shape3D sh = new Shape3D(label3D);

    bg.addChild(sh);

    PointArray pointArray = new PointArray(nNumPoints * nNumPoints,
            GeometryArray.COORDINATES | GeometryArray.COLOR_3);

    // create the PointArray that we will be rendering
    int nPoint = 0;
    final double factor = 1.0 / nNumPoints;

    for (int n = 0; n < nNumPoints; n++) {
      for (int i = 0; i < nNumPoints; i++) {
        Point3f point = new Point3f(n - nNumPoints / 2, i - nNumPoints
                / 2, 0.0f);
        pointArray.setCoordinate(nPoint, point);
        pointArray.setColor(nPoint++, new Color3f(0.5f,
                (float) (n * factor), (float) (i * factor)));
      }
    }

    // create the material for the points
    Appearance pointApp = new Appearance();

    // enlarge the points
    pointApp.setPointAttributes(new PointAttributes(nPointSize, bAliased));

    Shape3D pointShape = new Shape3D(pointArray, pointApp);

    bg.addChild(pointShape);
    return bg;
  }

  public static void main(String[] args) {
    StarMap pointTest = new StarMap();
    pointTest.saveCommandLineArguments(args);

    new MainFrame(pointTest, m_kWidth, m_kHeight);
  }
}

/*******************************************************************************
 * Copyright (C) 2001 Daniel Selman
 *
 * First distributed with the book "Java 3D Programming" by Daniel Selman and
 * published by Manning Publications. http://manning.com/selman
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, version 2.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * The license can be found on the WWW at: http://www.fsf.org/copyleft/gpl.html
 *
 * Or by writing to: Free Software Foundation, Inc., 59 Temple Place - Suite
 * 330, Boston, MA 02111-1307, USA.
 *
 * Authors can be contacted at: Daniel Selman: daniel@selman.org
 *
 * If you make changes you think others would like, please contact one of the
 * authors or someone at the www.j3d.org web site.
 ******************************************************************************/

//*****************************************************************************
/**
 * Java3dApplet
 *
 * Base class for defining a Java 3D applet. Contains some useful methods for
 * defining views and scenegraphs etc.
 *
 * @author Daniel Selman
 * @version 1.0
 */
//*****************************************************************************

abstract class Java3dApplet extends Applet {
  public static int m_kWidth = 300;

  public static int m_kHeight = 300;

  protected String[] m_szCommandLineArray = null;

  protected VirtualUniverse m_Universe = null;

  protected BranchGroup m_SceneBranchGroup = null;

  protected Bounds m_ApplicationBounds = null;

  //  protected com.tornadolabs.j3dtree.Java3dTree m_Java3dTree = null;

  public Java3dApplet() {
  }

  public boolean isApplet() {
    try {
      System.getProperty("user.dir");
      System.out.println("Running as Application.");
      return false;
    } catch (Exception e) {
    }

    System.out.println("Running as Applet.");
    return true;
  }

  public URL getWorkingDirectory() throws java.net.MalformedURLException {
    URL url = null;

    try {
      File file = new File(System.getProperty("user.dir"));
      System.out.println("Running as Application:");
      System.out.println("   " + file.toURL());
      return file.toURL();
    } catch (Exception e) {
    }

    System.out.println("Running as Applet:");
    System.out.println("   " + getCodeBase());

    return getCodeBase();
  }

  public VirtualUniverse getVirtualUniverse() {
    return m_Universe;
  }

  //public com.tornadolabs.j3dtree.Java3dTree getJ3dTree() {
  //return m_Java3dTree;
  //  }

  public Locale getFirstLocale() {
    java.util.Enumeration e = m_Universe.getAllLocales();

    if (e.hasMoreElements() != false)
      return (Locale) e.nextElement();

    return null;
  }

  protected Bounds getApplicationBounds() {
    if (m_ApplicationBounds == null)
      m_ApplicationBounds = createApplicationBounds();

    return m_ApplicationBounds;
  }

  protected Bounds createApplicationBounds() {
    m_ApplicationBounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
            100.0);
    return m_ApplicationBounds;
  }

  protected Background createBackground() {
    Background back = new Background(new Color3f(0.9f, 0.9f, 0.9f));
    back.setApplicationBounds(createApplicationBounds());
    return back;
  }

  public void initJava3d() {
    //  m_Java3dTree = new com.tornadolabs.j3dtree.Java3dTree();
    m_Universe = createVirtualUniverse();

    Locale locale = createLocale(m_Universe);

    BranchGroup sceneBranchGroup = createSceneBranchGroup();

    ViewPlatform vp = createViewPlatform();
    BranchGroup viewBranchGroup = createViewBranchGroup(
            getViewTransformGroupArray(), vp);

    createView(vp);

    Background background = createBackground();

    if (background != null)
      sceneBranchGroup.addChild(background);

    //    m_Java3dTree.recursiveApplyCapability(sceneBranchGroup);
    //  m_Java3dTree.recursiveApplyCapability(viewBranchGroup);

    locale.addBranchGraph(sceneBranchGroup);
    addViewBranchGroup(locale, viewBranchGroup);

    onDoneInit();
  }

  protected void onDoneInit() {
    //  m_Java3dTree.updateNodes(m_Universe);
  }

  protected double getScale() {
    return 1.0;
  }

  public TransformGroup[] getViewTransformGroupArray() {
    TransformGroup[] tgArray = new TransformGroup[1];
    tgArray[0] = new TransformGroup();

    // move the camera BACK a little...
    // note that we have to invert the matrix as
    // we are moving the viewer
    Transform3D t3d = new Transform3D();
    t3d.setScale(getScale());
    t3d.setTranslation(new Vector3d(0.0, 0.0, -20.0));
    t3d.invert();
    tgArray[0].setTransform(t3d);

    return tgArray;
  }

  protected void addViewBranchGroup(Locale locale, BranchGroup bg) {
    locale.addBranchGraph(bg);
  }

  protected Locale createLocale(VirtualUniverse u) {
    return new Locale(u);
  }

  protected BranchGroup createSceneBranchGroup() {
    m_SceneBranchGroup = new BranchGroup();
    return m_SceneBranchGroup;
  }

  protected View createView(ViewPlatform vp) {
    View view = new View();

    PhysicalBody pb = createPhysicalBody();
    PhysicalEnvironment pe = createPhysicalEnvironment();

    view.setPhysicalEnvironment(pe);
    view.setPhysicalBody(pb);

    if (vp != null)
      view.attachViewPlatform(vp);

    view.setBackClipDistance(getBackClipDistance());
    view.setFrontClipDistance(getFrontClipDistance());

    Canvas3D c3d = createCanvas3D();
    view.addCanvas3D(c3d);
    addCanvas3D(c3d);

    return view;
  }

  protected PhysicalBody createPhysicalBody() {
    return new PhysicalBody();
  }


  protected PhysicalEnvironment createPhysicalEnvironment() {
    return new PhysicalEnvironment();
  }

  protected float getViewPlatformActivationRadius() {
    return 100;
  }

  protected ViewPlatform createViewPlatform() {
    ViewPlatform vp = new ViewPlatform();
    vp.setViewAttachPolicy(View.RELATIVE_TO_FIELD_OF_VIEW);
    vp.setActivationRadius(getViewPlatformActivationRadius());

    return vp;
  }

  protected Canvas3D createCanvas3D() {
    GraphicsConfigTemplate3D gc3D = new GraphicsConfigTemplate3D();
    gc3D.setSceneAntialiasing(GraphicsConfigTemplate.PREFERRED);
    GraphicsDevice gd[] = GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getScreenDevices();

    Canvas3D c3d = new Canvas3D(gd[0].getBestConfiguration(gc3D));
    c3d.setSize(getCanvas3dWidth(c3d), getCanvas3dHeight(c3d));

    return c3d;
  }

  protected int getCanvas3dWidth(Canvas3D c3d) {
    return m_kWidth;
  }

  protected int getCanvas3dHeight(Canvas3D c3d) {
    return m_kHeight;
  }

  protected double getBackClipDistance() {
    return 100.0;
  }

  protected double getFrontClipDistance() {
    return 1.0;
  }

  protected BranchGroup createViewBranchGroup(TransformGroup[] tgArray,
                                              ViewPlatform vp) {
    BranchGroup vpBranchGroup = new BranchGroup();

    if (tgArray != null && tgArray.length > 0) {
      Group parentGroup = vpBranchGroup;
      TransformGroup curTg = null;

      for (int n = 0; n < tgArray.length; n++) {
        curTg = tgArray[n];
        parentGroup.addChild(curTg);
        parentGroup = curTg;
      }

      tgArray[tgArray.length - 1].addChild(vp);
    } else
      vpBranchGroup.addChild(vp);

    return vpBranchGroup;
  }

  protected void addCanvas3D(Canvas3D c3d) {
    setLayout(new BorderLayout());
    add(c3d, BorderLayout.CENTER);
    doLayout();
  }

  protected VirtualUniverse createVirtualUniverse() {
    return new VirtualUniverse();
  }

  protected void saveCommandLineArguments(String[] szArgs) {
    m_szCommandLineArray = szArgs;
  }

  protected String[] getCommandLineArguments() {
    return m_szCommandLineArray;
  }
}