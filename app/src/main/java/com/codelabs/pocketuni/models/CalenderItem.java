package com.codelabs.pocketuni.models;

public class CalenderItem {
    String eventBatch, eventBatchType, eventCourse, eventDate, eventTime, eventType, eventSubject, eventLecturer, eventHallName;

    public CalenderItem(String eventBatch, String eventBatchType, String eventCourse, String eventDate, String eventTime, String eventType, String eventSubject, String eventLecturer, String eventHallName) {
        this.eventBatch = eventBatch;
        this.eventBatchType = eventBatchType;
        this.eventCourse = eventCourse;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventType = eventType;
        this.eventSubject = eventSubject;
        this.eventLecturer = eventLecturer;
        this.eventHallName = eventHallName;
    }

    public String getEventBatch() {
        return eventBatch;
    }

    public void setEventBatch(String eventBatch) {
        this.eventBatch = eventBatch;
    }

    public String getEventBatchType() {
        return eventBatchType;
    }

    public void setEventBatchType(String eventBatchType) {
        this.eventBatchType = eventBatchType;
    }

    public String getEventCourse() {
        return eventCourse;
    }

    public void setEventCourse(String eventCourse) {
        this.eventCourse = eventCourse;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventSubject() {
        return eventSubject;
    }

    public void setEventSubject(String eventSubject) {
        this.eventSubject = eventSubject;
    }

    public String getEventLecturer() {
        return eventLecturer;
    }

    public void setEventLecturer(String eventLecturer) {
        this.eventLecturer = eventLecturer;
    }

    public String getEventHallName() {
        return eventHallName;
    }

    public void setEventHallName(String eventHallName) {
        this.eventHallName = eventHallName;
    }
}
//    String calenderCourse, calenderBatch, calenderBatchType, calenderDate, calenderDesc, calenderTime, calenderTitle;
//    String allocatedDate, allocationTime, allocationType, allocationBatch, allocationBatchType, allocationCourse, allocationHallName, allocationLecturer, allocationSubject;
//
//    public CalenderItem(String calenderCourse, String calenderBatch, String calenderBatchType, String calenderDate, String calenderDesc, String calenderTime, String calenderTitle) {
//        this.calenderCourse = calenderCourse;
//        this.calenderBatch = calenderBatch;
//        this.calenderBatchType = calenderBatchType;
//        this.calenderDate = calenderDate;
//        this.calenderDesc = calenderDesc;
//        this.calenderTime = calenderTime;
//        this.calenderTitle = calenderTitle;
//    }
//
//    public CalenderItem(String allocatedDate, String allocationTime, String allocationType, String allocationBatch, String allocationBatchType, String allocationCourse, String allocationHallName, String allocationLecturer, String allocationSubject) {
//        this.allocatedDate = allocatedDate;
//        this.allocationTime = allocationTime;
//        this.allocationType = allocationType;
//        this.allocationBatch = allocationBatch;
//        this.allocationBatchType = allocationBatchType;
//        this.allocationCourse = allocationCourse;
//        this.allocationHallName = allocationHallName;
//        this.allocationLecturer = allocationLecturer;
//        this.allocationSubject = allocationSubject;
//    }
//
//    public String getCalenderCourse() {
//        return calenderCourse;
//    }
//
//    public void setCalenderCourse(String calenderCourse) {
//        this.calenderCourse = calenderCourse;
//    }
//
//    public String getCalenderBatch() {
//        return calenderBatch;
//    }
//
//    public void setCalenderBatch(String calenderBatch) {
//        this.calenderBatch = calenderBatch;
//    }
//
//    public String getCalenderBatchType() {
//        return calenderBatchType;
//    }
//
//    public void setCalenderBatchType(String calenderBatchType) {
//        this.calenderBatchType = calenderBatchType;
//    }
//
//    public String getCalenderDate() {
//        return calenderDate;
//    }
//
//    public void setCalenderDate(String calenderDate) {
//        this.calenderDate = calenderDate;
//    }
//
//    public String getCalenderDesc() {
//        return calenderDesc;
//    }
//
//    public void setCalenderDesc(String calenderDesc) {
//        this.calenderDesc = calenderDesc;
//    }
//
//    public String getCalenderTime() {
//        return calenderTime;
//    }
//
//    public void setCalenderTime(String calenderTime) {
//        this.calenderTime = calenderTime;
//    }
//
//    public String getCalenderTitle() {
//        return calenderTitle;
//    }
//
//    public void setCalenderTitle(String calenderTitle) {
//        this.calenderTitle = calenderTitle;
//    }
//
//    public String getAllocatedDate() {
//        return allocatedDate;
//    }
//
//    public void setAllocatedDate(String allocatedDate) {
//        this.allocatedDate = allocatedDate;
//    }
//
//    public String getAllocationTime() {
//        return allocationTime;
//    }
//
//    public void setAllocationTime(String allocationTime) {
//        this.allocationTime = allocationTime;
//    }
//
//    public String getAllocationType() {
//        return allocationType;
//    }
//
//    public void setAllocationType(String allocationType) {
//        this.allocationType = allocationType;
//    }
//
//    public String getAllocationBatch() {
//        return allocationBatch;
//    }
//
//    public void setAllocationBatch(String allocationBatch) {
//        this.allocationBatch = allocationBatch;
//    }
//
//    public String getAllocationBatchType() {
//        return allocationBatchType;
//    }
//
//    public void setAllocationBatchType(String allocationBatchType) {
//        this.allocationBatchType = allocationBatchType;
//    }
//
//    public String getAllocationCourse() {
//        return allocationCourse;
//    }
//
//    public void setAllocationCourse(String allocationCourse) {
//        this.allocationCourse = allocationCourse;
//    }
//
//    public String getAllocationHallName() {
//        return allocationHallName;
//    }
//
//    public void setAllocationHallName(String allocationHallName) {
//        this.allocationHallName = allocationHallName;
//    }
//
//    public String getAllocationLecturer() {
//        return allocationLecturer;
//    }
//
//    public void setAllocationLecturer(String allocationLecturer) {
//        this.allocationLecturer = allocationLecturer;
//    }
//
//    public String getAllocationSubject() {
//        return allocationSubject;
//    }
//
//    public void setAllocationSubject(String allocationSubject) {
//        this.allocationSubject = allocationSubject;
//    }