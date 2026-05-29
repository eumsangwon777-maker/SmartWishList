import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SmartWishListApp {
    public static void main(String[] args) {
        // 1. 메인 윈도우 창 생성
        JFrame mainFrame = new JFrame("의류 필요성 기반 스마트 위시리스트");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000, 600);
        mainFrame.setLocationRelativeTo(null);

        // 2. 상단 대시보드 패널
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(240, 240, 240));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel budgetLabel = new JLabel("[대시보드] 이번 달 남은 쇼핑 예산: 150,000원 | 추천: 지금 가장 필요한 옷은 '검정 슬랙스'입니다.");
        budgetLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        topPanel.add(budgetLabel);
        mainFrame.add(topPanel, BorderLayout.NORTH);

        // 3. 중앙 데이터 표 세팅
        String[] columns = { "카테고리", "상품명", "가격", "필요성 점수 (1~10)", "스타일 태그", "등록일" };
        String[][] data = {
                { "하의", "와이드 데님 팬츠", "45,000원", "9.5", "스트릿", "05-29" },
                { "상의", "오버핏 흰색 반팔티", "22,000원", "8.2", "캐주얼", "05-29" },
                { "아우터", "미니멀 가죽 자켓", "180,000원", "4.0", "미니멀", "05-29" }
        };

        DefaultTableModel tableModel = new DefaultTableModel(data, columns);
        JTable table = new JTable(tableModel);

        // ⭐️ [치트키 기능] 클릭 시 자동 정렬되는 기능 추가!
        table.setAutoCreateRowSorter(true);

        table.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        table.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(table);
        mainFrame.add(scrollPane, BorderLayout.CENTER);

        // 4. 하단 제어 버튼 패널 및 이벤트 추가
        JPanel bottomPanel = new JPanel();
        JButton addButton = new JButton("새 위시리스트 추가");
        JButton deleteButton = new JButton("선택 삭제");

        bottomPanel.add(addButton);
        bottomPanel.add(deleteButton);
        mainFrame.add(bottomPanel, BorderLayout.SOUTH);

        // [기능 1] 새 위시리스트 추가 버튼 클릭 시 입력 창 띄우기
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField categoryField = new JTextField();
                JTextField nameField = new JTextField();
                JTextField priceField = new JTextField();
                JTextField scoreField = new JTextField("5.0"); // 기본값
                JTextField tagField = new JTextField();

                Object[] message = {
                        "카테고리 (상의/하의/아우터 등):", categoryField,
                        "상품명:", nameField,
                        "가격:", priceField,
                        "필요성 점수 (1~10점):", scoreField,
                        "스타일 태그 (스트릿/캐주얼 등):", tagField
                };

                int option = JOptionPane.showConfirmDialog(mainFrame, message, "새 위시리스트 아이템 추가",
                        JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    // 입력된 데이터를 표에 한 줄 추가
                    String[] newRow = {
                            categoryField.getText(),
                            nameField.getText(),
                            priceField.getText() + "원",
                            scoreField.getText(),
                            tagField.getText(),
                            "05-29" // 등록일 기본값
                    };
                    tableModel.addRow(newRow);
                }
            }
        });

        // [기능 2] 선택 삭제 버튼 클릭 시 해당 줄 지우기
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    tableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "삭제할 항목을 표에서 먼저 선택해 주세요.");
                }
            }
        });

        // 5. 창 시각화
        mainFrame.setVisible(true);
    }
}