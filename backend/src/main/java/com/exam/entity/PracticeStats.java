package com.exam.entity;

/**
 * 练习统计信息实体类
 */
public class PracticeStats {
    private Integer totalPracticeCount; // 总练习次数
    private Integer totalQuestionCount; // 总练习题目数
    private Integer totalCorrectCount; // 总正确题目数
    private Double averageScore; // 平均得分
    
    // getter and setter methods
    public Integer getTotalPracticeCount() {
        return totalPracticeCount;
    }
    
    public void setTotalPracticeCount(Integer totalPracticeCount) {
        this.totalPracticeCount = totalPracticeCount;
    }
    
    public Integer getTotalQuestionCount() {
        return totalQuestionCount;
    }
    
    public void setTotalQuestionCount(Integer totalQuestionCount) {
        this.totalQuestionCount = totalQuestionCount;
    }
    
    public Integer getTotalCorrectCount() {
        return totalCorrectCount;
    }
    
    public void setTotalCorrectCount(Integer totalCorrectCount) {
        this.totalCorrectCount = totalCorrectCount;
    }
    
    public Double getAverageScore() {
        return averageScore;
    }
    
    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }
}
