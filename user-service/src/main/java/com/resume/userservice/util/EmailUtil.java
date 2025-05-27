package com.resume.userservice.util;

import com.resume.userservice.resume.dto.Education;
import com.resume.userservice.resume.dto.Experience;
import com.resume.userservice.resume.response.ResumeVersionResponse;

import java.util.List;
import java.util.Map;

public class EmailUtil {
    public static String buildEmailBody(String messageBody, ResumeVersionResponse response) {
        StringBuilder builder = new StringBuilder();

        builder.append("<html><body>");
        builder.append("<h2>").append("Resume Details").append("</h2>");
        builder.append("<p>").append(messageBody).append("</p>");

        builder.append("<h3>Basic Info</h3>");
        builder.append("<ul>")
                .append("<li><strong>ID:</strong> ").append(response.getId()).append("</li>")
                .append("<li><strong>Version:</strong> ").append(response.getVersionNumber()).append("</li>")
                .append("<li><strong>Name:</strong> ").append(response.getName()).append("</li>")
                .append("<li><strong>Email:</strong> ").append(response.getEmail()).append("</li>")
                .append("<li><strong>Phone:</strong> ").append(response.getPhone()).append("</li>")
                .append("<li><strong>Primary:</strong> ").append(response.isPrimary()).append("</li>")
                .append("<li><strong>Draft:</strong> ").append(response.isDraft()).append("</li>")
                .append("</ul>");

        builder.append("<h3>Skills</h3><ul>");
        for (String skill : response.getSkills()) {
            builder.append("<li>").append(skill).append("</li>");
        }
        builder.append("</ul>");

        builder.append("<h3>Experiences</h3>");
        for (Experience exp : response.getExperiences()) {
            builder.append("<div>")
                    .append("<strong>Title:</strong> ").append(exp.getRole()).append("<br>")
                    .append("<strong>Company:</strong> ").append(exp.getCompany()).append("<br>")
                    .append("<strong>Duration:</strong> ").append(exp.getFrom()).append(" - ").append(exp.getTo()).append("<br>")
                    .append("<strong>Description:</strong> ").append(exp.getDescription()).append("<br><br>")
                    .append("</div>");
        }

        builder.append("<h3>Education</h3>");
        for (Education edu : response.getEducation()) {
            builder.append("<div>")
                    .append("<strong>Degree:</strong> ").append(edu.getDegree()).append("<br>")
                    .append("<strong>Institution:</strong> ").append(edu.getInstitution()).append("<br>")
                    .append("<strong>Duration:</strong> ").append(edu.getFrom()).append(" - ").append(edu.getTo()).append("<br><br>")
                    .append("</div>");
        }

        builder.append("<h3>Custom Sections</h3>");
        for (Map.Entry<String, List<String>> entry : response.getCustomSections().entrySet()) {
            builder.append("<strong>").append(entry.getKey()).append("</strong><ul>");
            for (String val : entry.getValue()) {
                builder.append("<li>").append(val).append("</li>");
            }
            builder.append("</ul>");
        }

        builder.append("</body></html>");
        return builder.toString();
    }

}
