package ui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import br.gerenciamento.model.*;
import repository.MySQLRepository;

public class FormListagem extends JFrame {
    public FormListagem() {
        setTitle("Listagem de Usuários");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        JButton btnDeletar = new JButton("Deletar");
        btnDeletar.setSize(new Dimension(0,20));
        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setSize(new Dimension(0,20));
        JPanel pnlBotoes = new JPanel();
        pnlBotoes.setLayout(new GridLayout(1,2));
        pnlBotoes.add(btnAtualizar);
        pnlBotoes.add(btnDeletar);
        add(pnlBotoes, BorderLayout.NORTH);

        // Definir colunas da tabela (SEM A SENHA!)
        String[] colunas = {"ID", "Nome", "Email", "Telefone"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        JTable tabela = new JTable(modelo);
        atualizarTabela(modelo);
        add(new JScrollPane(tabela), BorderLayout.CENTER);
        
        btnDeletar.addActionListener(e ->
        {
            int linha = tabela.getSelectedRow();
            int id = (int) tabela.getValueAt(linha,0);
            try {
                MySQLRepository.getInstance().deletar(id);
                JOptionPane.showMessageDialog(this, "Usuário apagado com sucesso!");
                modelo.setRowCount(0);
                atualizarTabela(modelo);
                
            } catch (Exception ex) {
                System.getLogger(FormListagem.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            
        });
            
        btnAtualizar.addActionListener(e->
        {
            int linha = tabela.getSelectedRow();
            int id = (int) tabela.getValueAt(linha,0);
            try{
                new AtualizarUsuario(id).setVisible(true);
            } catch (Exception exA) {System.out.println(exA.getMessage());}
        });
    }
    public void atualizarTabela(DefaultTableModel modelo) {
        // Buscar dados usando nosso Singleton
        List<Usuario> usuarios = MySQLRepository.getInstance().listarUsuarios();
        
        for (Usuario u : usuarios) {
            
            // Adiciona as linhas à tabela (omitindo u.getSenha())
            modelo.addRow(new Object[]{
                u.getId(), u.getNome(), u.getEmail(), u.getTelefone()
            });
        }
    }
}