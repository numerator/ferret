package com.google.research.ic.ferret.test;

import static org.junit.Assert.fail;

import com.google.research.ic.ferret.data.Event;
import com.google.research.ic.ferret.data.LogLoader;
import com.google.research.ic.ferret.data.Snippet;
import com.google.research.ic.ferret.data.ext.alogger.AccessibilityLogEvent;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * TODO: Insert description here. (generated by marknewman)
 */
public class LogTest {

  @Test
  public void testLogLoading() {
    try {
      LogLoader loader = LogLoader.getLogLoader();
      loader.setLogType(LogLoader.ACCESSIBILITIY_LOG);
      loader.loadLogs();

      List<Snippet> snippets = loader.loadLogs();
      Debug.log("Read in " + snippets.size() + " segments");
      for (Iterator<Snippet> iterator = snippets.iterator(); iterator.hasNext();) {
        Snippet segment = iterator.next();
        ArrayList<Event> events = segment.getEvents();
        for (Iterator<Event> iterator2 = events.iterator(); iterator2.hasNext();) {
          Event genericEvent = iterator2.next();
          HashMap<String, String> theMap = genericEvent.getKeyValuePairs();
          Debug.log("The event " + genericEvent.getComponentDescription() + " has " + theMap.size() + " key-value pairs");
          Debug.log("... and the widget class is " + ((AccessibilityLogEvent) genericEvent).getwClassName());
        }
      }
    } catch (Exception e) {
      fail("Exception was thrown" + e);
    }   
  }
}